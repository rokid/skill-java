package com.rokid.skill.kit4j.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wuyukai
 * @date 2018/7/22
 */
@Slf4j
public class JacksonUtil {

  private static ObjectMapper mObjectMapper;

  /**
   * Creates an {@link ObjectMapper} for mapping json objects. Mapper can be configured here
   *
   * @return created {@link ObjectMapper}
   */
  private static ObjectMapper getMapper() {
    if (mObjectMapper == null) {
      mObjectMapper = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
    return mObjectMapper;
  }

  /**
   * Maps json string to specified class
   *
   * @param json string to parse
   * @param tClass class of object in which json will be parsed
   * @param <T> generic parameter for tClass
   * @return mapped T class instance
   */
  public static <T> T toObject(String json, Class<T> tClass) {
    if (StringUtils.isBlank(json)) {
      return null;
    }
    try {
      return getMapper().readValue(json, tClass);
    } catch (IOException e) {
      log.error("convert to object error : {}", e);
      return null;
    }
  }

  /**
   * Maps json string to {@link ArrayList} of specified class object instances
   *
   * @param json string to parse
   * @param tClass class of object in which json will be parsed
   * @param <T> generic parameter for tClass
   * @return mapped T class instance
   */
  public static <T> ArrayList<T> arrayList(String json, Class<T> tClass) {
    if (StringUtils.isBlank(json)) {
      return null;
    }
    TypeFactory typeFactory = getMapper().getTypeFactory();
    JavaType type = typeFactory.constructCollectionType(ArrayList.class, tClass);
    try {
      return getMapper().readValue(json, type);
    } catch (Exception e) {
      log.error("convert to json error : {}", e);
      return null;
    }

  }

  /**
   * Writes specified object as string
   *
   * @param object object to write
   * @return result json
   */
  public static String toJson(Object object) {
    try {
      return getMapper().writeValueAsString(object);
    } catch (JsonProcessingException e) {
      log.error("convert to json error : {}", e);
      return null;
    }
  }

  public static <K, V> Map<K, V> toMap(String json, Class<K> kClass, Class<V> vClass) {
    if (StringUtils.isBlank(json)) {
      return null;
    }
    TypeFactory typeFactory = getMapper().getTypeFactory();
    JavaType type = typeFactory.constructMapType(HashMap.class, kClass, vClass);
    try {
      return getMapper().readValue(json, type);
    } catch (Exception e) {
      log.error("convert to json error : {}", e);
      return null;
    }

  }

}
