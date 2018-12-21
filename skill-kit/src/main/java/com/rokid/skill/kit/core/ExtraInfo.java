package com.rokid.skill.kit.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 拓展信息服务，用于零时存储本次请求的零时变量信息.
 *
 * @author wuyukai
 * @date 2018/12/16
 */
public class ExtraInfo {

  private static final ThreadLocal<Map<String, Object>> COPY_ON_THREAD_LOCAL = new ThreadLocal<>();

  private static final int WRITE_OPERATION = 1;
  private static final int MAP_COPY_OPERATION = 2;

  private static final ThreadLocal<Integer> LAST_OPERATION = new ThreadLocal<Integer>();

  private static Integer getAndSetLastOperation(int op) {
    Integer lastOp = LAST_OPERATION.get();
    LAST_OPERATION.set(op);
    return lastOp;
  }

  private static boolean wasLastOpReadOrNull(Integer lastOp) {
    return lastOp == null || lastOp == MAP_COPY_OPERATION;
  }

  private static Map<String, Object> duplicateAndInsertNewMap(Map<String, Object> oldMap) {
    Map<String, Object> newMap = Collections.synchronizedMap(new HashMap<>(8));
    if (oldMap != null) {
      // we don't want the parent thread modifying oldMap while we are
      // iterating over it
      synchronized (oldMap) {
        newMap.putAll(oldMap);
      }
    }

    COPY_ON_THREAD_LOCAL.set(newMap);
    return newMap;
  }

  /**
   * Put a context value (the <code>val</code> parameter) as identified with the
   * <code>key</code> parameter into the current thread's context map. Note that
   * contrary to log4j, the <code>val</code> parameter can be null.
   * <p/>
   * <p/>
   * If the current thread does not have a context map it is created as a side effect of this call.
   *
   * @throws IllegalArgumentException in case the "key" parameter is null
   */
  public static void put(String key, Object val) throws IllegalArgumentException {
    if (key == null) {
      throw new IllegalArgumentException("key cannot be null");
    }

    Map<String, Object> oldMap = COPY_ON_THREAD_LOCAL.get();
    Integer lastOp = getAndSetLastOperation(WRITE_OPERATION);

    if (wasLastOpReadOrNull(lastOp) || oldMap == null) {
      Map<String, Object> newMap = duplicateAndInsertNewMap(oldMap);
      newMap.put(key, val);
    } else {
      oldMap.put(key, val);
    }
  }

  /**
   * 删除制定Key的缓存变量.
   */
  public static void remove(String key) {
    if (key == null) {
      return;
    }
    Map<String, Object> oldMap = COPY_ON_THREAD_LOCAL.get();
    if (oldMap == null) {
      return;
    }

    Integer lastOp = getAndSetLastOperation(WRITE_OPERATION);

    if (wasLastOpReadOrNull(lastOp)) {
      Map<String, Object> newMap = duplicateAndInsertNewMap(oldMap);
      newMap.remove(key);
    } else {
      oldMap.remove(key);
    }
  }

  /**
   * 清除本次请求的所有缓存变量.
   */
  public static void clear() {
    LAST_OPERATION.remove();
    COPY_ON_THREAD_LOCAL.remove();
  }

  /**
   * 获取指定key的缓存变量.
   *
   * @param key is not null
   */
  public static Object get(String key) {
    final Map<String, Object> map = COPY_ON_THREAD_LOCAL.get();
    if ((map != null) && (key != null)) {
      return map.get(key);
    } else {
      return null;
    }
  }


  private static Map<String, Object> getPropertyMap() {
    LAST_OPERATION.set(MAP_COPY_OPERATION);
    return COPY_ON_THREAD_LOCAL.get();
  }

  /**
   * 获取零时缓存的变量Key值信息.
   */
  public static Set<String> getKeys() {
    Map<String, Object> map = getPropertyMap();

    if (map != null) {
      return map.keySet();
    } else {
      return null;
    }
  }

  /**
   * 获取上下文存储的拷贝信息.
   */
  public static Map<String, Object> getCopyOfContextMap() {
    Map<String, Object> hashMap = COPY_ON_THREAD_LOCAL.get();
    if (hashMap == null) {
      return null;
    } else {
      return new HashMap<>(hashMap);
    }
  }

  /**
   * 设置上下文缓存的Map信息.
   */
  public static void setContextMap(Map<String, Object> contextMap) {
    LAST_OPERATION.set(WRITE_OPERATION);

    Map<String, Object> newMap = Collections.synchronizedMap(new HashMap<>(8));
    newMap.putAll(contextMap);

    COPY_ON_THREAD_LOCAL.set(newMap);
  }
}
