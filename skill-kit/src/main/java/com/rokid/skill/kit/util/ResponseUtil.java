package com.rokid.skill.kit.util;

import com.rokid.skill.protocol.exception.ProtocolException;
import com.rokid.skill.protocol.response.ResResponse;
import com.rokid.skill.protocol.response.response.ResResponseContent;
import com.rokid.skill.protocol.response.response.action.ResAction;
import com.rokid.skill.protocol.response.response.action.directive.ResDirective;
import com.rokid.skill.protocol.response.response.action.directive.confirm.ResConfirm;
import com.rokid.skill.protocol.response.response.action.directive.media.ResMedia;
import com.rokid.skill.protocol.response.response.action.directive.media.mediaitem.ResMediaItem;
import com.rokid.skill.protocol.response.response.action.directive.pickup.ResPickup;
import com.rokid.skill.protocol.response.response.action.directive.voice.ResVoice;
import com.rokid.skill.protocol.response.response.action.directive.voice.item.ResVoiceItem;
import com.rokid.skill.protocol.response.response.card.ResCard;
import com.rokid.skill.protocol.response.response.card.chat.ResAccountLinkCard;
import com.rokid.skill.protocol.response.response.card.chat.ResChatCard;
import com.rokid.skill.protocol.response.session.ResSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * 类描述: 用于快速构造CloudApp的Response的工具类 对应结构以及命名: Response对应ResResponse：协议内容；.
 * response对应ResResponseContent：响应； action对应ResAction：事件； voice：文本； media：媒体； confirm:主动询问；
 * confirmIntent：主动询问意图（比如询问歌手）； confirmSlot：主动询问意图内容（比如歌手）； optionWords：主动询问辅助信息；
 * 场景化：scene；非场景化：cut
 *
 * @author wuyukai
 */
public class ResponseUtil {

  /**
   * 方法: 构造一个退出应用的协议内容 描述: 客户端接收到该协议内容以后会立即退出应用 使用场景: 当我们的应用需要退出的时候返回这个协议内容 注意:.
   * 此协议内容的版本以及事件版本号都已经被固定为RES_VERSION_V2和ACTION_VERSION_V2.
   * 如协议版本变更，请自行修正一下；另外应用退出以后，nlp那边的session也会被清理
   */
  public static ResResponse buildExitNowResponse() throws ProtocolException {
    return ResponseBuilder.build().finishNow().create();
  }

  /**
   * 方法: 构造一个空的协议内容，Voice以及Media继续保持原有执行状态 描述: 客户端接收到该协议内容以后不会对当前的操作有任何的影响 使用场景:.
   * 当我们的应用接受到不需要处理，需要忽略的Event请求是，我们可以返回该响应.
   * 注意:此协议内容的版本以及事件版本号都已经被固定为RES_VERSION_V2和ACTION_VERSION_V2,
   * 如协议版本变更，请自行修正一下，另外此协议内容会的shouldEndSession=false的
   */
  public static ResResponse buildIngoreEventResponse() {
    return ResponseBuilder.build().createEmpty();
  }

  /**
   * 播报一句TTS,Media继续保持原有执行状态,并且有EventRequest.
   *
   * @param voiceId tts的ID，可以为空
   * @param voiceContent tts 内容
   */
  public static ResResponse buildVoicePlayResponse(String voiceId, String voiceContent)
      throws ProtocolException {
    if (StringUtils.isBlank(voiceContent)) {
      throw new ProtocolException("voiceContent Can not be null");
    }
    return ResponseBuilder.build().voicePlay(voiceId, voiceContent).create();
  }

  /**
   * 暂停播放Voice.
   */
  public static ResResponse buildVoicePauseResponse() throws ProtocolException {
    return ResponseBuilder.build().voicePause().create();
  }

  /**
   * 继续播放Voice.
   */
  public static ResResponse buildVoiceResumeResponse() throws ProtocolException {
    return ResponseBuilder.build().voiceResume().create();
  }

  /**
   * 停止播放Voice.
   */
  public static ResResponse buildVoiceStopResponse() throws ProtocolException {
    return ResponseBuilder.build().voiceStop().create();
  }

  /**
   * 播放Media事件，TTS继续保持原有执行状态.
   */
  public static ResResponse buildMediaPlayResponse(String mediaId, String mediaToken,
      String mediaType,
      String mediaUrl, long mediaOffset) throws ProtocolException {
    if (StringUtils.isBlank(mediaUrl)) {
      throw new ProtocolException("mediaUrl Can not be null");
    }
    if (mediaOffset < 0) {
      throw new ProtocolException("mediaOffset Can not be < 0");
    }
    if (!ResMediaItem.MEDIA_TYPE_AUDIO.equals(mediaType) && ResMediaItem.MEDIA_TYPE_VIDEO
        .equals(mediaType)) {
      throw new ProtocolException("mediaType is error");
    }
    return ResponseBuilder.build()
        .mediaPlay(mediaId, mediaToken, mediaType, mediaUrl, mediaOffset).create();
  }

  /**
   * 暂停播放Media.
   */
  public static ResResponse buildMediaPauseResponse() throws ProtocolException {
    return ResponseBuilder.build().mediaPause().create();
  }

  /**
   * 继续播放Media.
   */
  public static ResResponse buildMediaResumeResponse() throws ProtocolException {
    return ResponseBuilder.build().mediaResume().create();
  }

  /**
   * 停止播放Media.
   */
  public static ResResponse buildMediaStopResponse() throws ProtocolException {
    return ResponseBuilder.build().mediaStop().create();
  }

  /**
   * 暂停播放Media和Voice.
   */
  public static ResResponse buildPauseResponse() throws ProtocolException {
    return ResponseBuilder.build().voicePause().mediaPause().create();
  }

  /**
   * 继续播放Voice和 Media.
   */
  public static ResResponse buildResumeResponse() throws ProtocolException {
    return ResponseBuilder.build().voiceResume().mediaResume().create();
  }

  /**
   * 停止播放Voice和Media.
   */
  public static ResResponse buildStopResponse() throws ProtocolException {
    return ResponseBuilder.build().voiceStop().mediaStop().create();
  }

  /**
   * 构造一个Confirm，并且把Media停止.
   *
   * @param voiceContent tts内容
   * @param confirmIntent 需要在平台上面定义过的intent，不能随便写
   * @param confirmSlot 需要在平台上面定义过的slot，不能随便写
   * @param confirmOptionWords 可选值
   * @return 协议异常
   */
  public static ResResponse buildConfirmOpenResponse(String voiceContent, String confirmIntent,
      String confirmSlot, int retryCount,
      List<String> confirmOptionWords) throws ProtocolException {
    if (StringUtils.isBlank(voiceContent)) {
      throw new ProtocolException("voiceContent Can not be null");
    }
    if (StringUtils.isBlank(confirmIntent)) {
      throw new ProtocolException("confirmIntent Can not be null");
    }
    if (StringUtils.isBlank(confirmSlot)) {
      throw new ProtocolException("confirmSlot Can not be null");
    }
    return ResponseBuilder.build().voicePlay(null, voiceContent).mediaStop()
        .confirmOpen(confirmIntent, confirmSlot, retryCount, confirmOptionWords).create();
  }


  /**
   * 响应构造器.
   *
   * @author wuyukai
   */
  public static class ResponseBuilder {

    private ResResponse response;
    /**
     * 执行完这个action完毕.
     */
    private boolean shouldEndSession = false;
    private String actionType = ResAction.ACTION_TYPE_NORMAL;
    private Map<String, Object> attributes;
    private ResVoice resVoice;
    private ResConfirm resConfirm;
    private ResMedia resMedia;
    private ResPickup pickup;
    private ResCard card;

    public static ResponseBuilder build() {
      return new ResponseBuilder();
    }

    /**
     * Skill协议响应构造器.
     */
    public ResponseBuilder() {
      response = buildEmptyActionResponse(ResResponse.RES_VERSION_V2, null,
          ResAction.ACTION_VERSION_V2, actionType,
          shouldEndSession);
    }

    /**
     * 下一次请求原样带回的参数.
     */
    public ResponseBuilder setAttributes(Map<String, Object> attributes) {
      this.attributes = attributes;
      return this;
    }

    /**
     * 播放TTS.
     */
    public ResponseBuilder voicePlay(
        String voiceId, String voiceContent) {
      if (resVoice == null) {
        resVoice = new ResVoice();
      }
      ResVoiceItem resVoiceItem = new ResVoiceItem();
      resVoiceItem.setItemId(voiceId);
      resVoiceItem.setTts(voiceContent);
      resVoice.setAction(ResVoice.VOICE_ACTION_PLAY);
      resVoice.setItem(resVoiceItem);
      return this;
    }

    /**
     * 暂停Media.
     */
    public ResponseBuilder voicePause() {
      if (resVoice == null) {
        resVoice = new ResVoice();
      }
      resVoice.setAction(ResVoice.VOICE_ACTION_PAUSE);
      return this;
    }

    /**
     * 继续播放TTS.
     */
    public ResponseBuilder voiceResume() {
      if (resVoice == null) {
        resVoice = new ResVoice();
      }
      resVoice.setAction(ResVoice.VOICE_ACTION_RESUME);
      return this;
    }

    /**
     * 停止播放TTS.
     */
    public ResponseBuilder voiceStop() {
      if (resVoice == null) {
        resVoice = new ResVoice();
      }
      resVoice.setAction(ResVoice.VOICE_ACTION_STOP);
      return this;
    }

    /**
     * 关闭Voice的EventRequest.
     */
    public ResponseBuilder voiceEventDisable() {
      if (resVoice == null) {
        resVoice = new ResVoice();
      }
      resVoice.setDisableEvent(true);
      return this;
    }

    /**
     * 构建一个TTS，不需要进行等待.
     */
    public ResponseBuilder voiceNoWait() {
      if (resVoice == null) {
        resVoice = new ResVoice();
      }
      resVoice.setNoWait(true);
      return this;
    }

    /**
     * 构造一个媒体对象，不需要进行等待.
     */
    public ResponseBuilder mediaNoWait() {
      if (resMedia == null) {
        resMedia = new ResMedia();
      }
      resMedia.setNoWait(true);
      return this;
    }

    /**
     * 构建一个Media，不需要进行音频进行压制.
     */
    public ResponseBuilder disableSuppress() {
      if (resMedia == null) {
        resMedia = new ResMedia();
      }
      resMedia.setDisableSuppress(true);
      return this;
    }

    /**
     * 发起confirm.
     */
    public ResponseBuilder confirmOpen(
        String confirmIntent, String confirmSlot, int retryCount,
        List<String> optionWords) {
      if (resConfirm == null) {
        resConfirm = new ResConfirm();
      }
      resConfirm.setConfirmSlot(confirmSlot);
      resConfirm.setConfirmIntent(confirmIntent);
      resConfirm.setRetryCount(retryCount);
      resConfirm.setOptionWords(optionWords);
      return this;
    }

    /**
     * 播放音频.
     */
    public ResponseBuilder audioPlay(String audioId, String audioToken, String audioUrl,
        long offsetInMilliseconds) {
      return mediaPlay(audioId, audioToken, ResMediaItem.MEDIA_TYPE_AUDIO, audioUrl,
          offsetInMilliseconds);
    }

    /**
     * 播放视频.
     */
    public ResponseBuilder videoPlay(String mediaId, String videoToken, String videoUrl,
        long offsetInMilliseconds) {
      return mediaPlay(mediaId, videoToken, ResMediaItem.MEDIA_TYPE_VIDEO, videoUrl,
          offsetInMilliseconds);
    }

    /**
     * 播放Media.
     */
    public ResponseBuilder mediaPlay(String mediaId, String mediaToken, String mediaType,
        String mediaUrl,
        long offsetInMilliseconds) {
      if (resMedia == null) {
        resMedia = new ResMedia();
      }
      ResMediaItem resMediaItem = new ResMediaItem();
      resMediaItem.setItemId(mediaId);
      resMediaItem.setType(mediaType);
      //resMediaItem.setToken(mediaToken);
      resMediaItem.setUrl(mediaUrl);
      resMediaItem.setOffsetInMilliseconds(offsetInMilliseconds);
      resMedia.setAction(ResMedia.MEDIA_ACTION_PLAY);
      resMedia.setItem(resMediaItem);
      return this;
    }

    /**
     * 暂停Media.
     */
    public ResponseBuilder mediaPause() {
      if (resMedia == null) {
        resMedia = new ResMedia();
      }
      resMedia.setAction(ResMedia.MEDIA_ACTION_PAUSE);
      return this;
    }

    /**
     * 继续播放 Media.
     */
    public ResponseBuilder mediaResume() {
      if (resMedia == null) {
        resMedia = new ResMedia();
      }
      resMedia.setAction(ResMedia.MEDIA_ACTION_RESUME);
      return this;
    }

    /**
     * 停止播放TTS.
     */
    public ResponseBuilder mediaStop() {
      if (resMedia == null) {
        resMedia = new ResMedia();
      }
      resMedia.setAction(ResMedia.MEDIA_ACTION_STOP);
      return this;
    }

    /**
     * 关闭Voice的EventRequest.
     */
    public ResponseBuilder mediaEventDisable() {
      if (resMedia == null) {
        resMedia = new ResMedia();
      }
      resMedia.setDisableEvent(true);
      return this;
    }

    /**
     * 打开拾音.
     */
    public ResponseBuilder pickupOpen(int retryCount) {
      return pickupOpen(retryCount, null);
    }

    /**
     * 打开识音，并且设置重复播报的TTS.
     */
    public ResponseBuilder pickupOpen(int retryCount, String retryTts) {
      if (pickup == null) {
        pickup = new ResPickup();
      }
      pickup.setEnable(true);
      pickup.setRetryCount(retryCount);
      pickup.setDurationInMilliseconds(6000L);
      if (StringUtils.isNotBlank(retryTts)) {
        pickup.setRetryTts(retryTts);
      }
      return this;
    }

    /**
     * 执行完毕以后退出应用.
     */
    public ResponseBuilder afterFinish() {
      shouldEndSession = true;
      return this;
    }

    /**
     * 立即退出应用.
     */
    public ResponseBuilder finishNow() {
      actionType = ResAction.ACTION_TYPE_EXIT;
      shouldEndSession = true;
      return this;
    }

    public ResponseBuilder sendChatCard(String content) {
      card = new ResChatCard(content);
      return this;
    }

    public ResponseBuilder sendAccountLinkCard() {
      card = new ResAccountLinkCard();
      return this;
    }

    /**
     * 通过Build创建一个响应.
     */
    public ResResponse create() throws ProtocolException {
      response.getSession().setAttributes(attributes);
      List<ResDirective> directives = new ArrayList<>();
      response.getResponse().getAction().setDirectives(directives);
      response.getResponse().getAction().setShouldEndSession(shouldEndSession);
      if (!ActionUtil.checkActionType(actionType)) {
        throw new ProtocolException("actionType is error");
      }
      response.getResponse().getAction().setType(actionType);
      if (!ActionUtil.checkVoice(resVoice)) {
        throw new ProtocolException("resVoice is error");
      }
      if (resVoice != null) {
        response.getResponse().getAction().getDirectives().add(resVoice);
      }
      if (!ActionUtil.checkMedia(resMedia)) {
        throw new ProtocolException("resMedia is error");
      }
      if (resMedia != null) {
        response.getResponse().getAction().getDirectives().add(resMedia);
      }
      if (!ActionUtil.checkConfirm(resConfirm)) {
        throw new ProtocolException("resConfirm is error");
      }
      if (resConfirm != null) {
        response.getResponse().getAction().getDirectives().add(resConfirm);
      }
      if (!ActionUtil.checkPickup(pickup)) {
        throw new ProtocolException("resPickup is error");
      }
      if (pickup != null) {
        response.getResponse().getAction().getDirectives().add(pickup);
      }
      if (card != null) {
        response.getResponse().setCard(card);
      }
      return response;
    }

    private ResResponse buildEmptyActionResponse(String resVersion,
        Map<String, Object> attributes,
        String actionVersion, String actionType, boolean shouldEndSession) {
      ResResponse resResponse = new ResResponse();
      resResponse.setVersion(resVersion);
      ResSession resSession = new ResSession();
      if (attributes != null && !attributes.isEmpty()) {
        resSession.setAttributes(attributes);
      }
      resResponse.setSession(resSession);
      ResResponseContent resResponseContent = new ResResponseContent();
      ResAction resAction;
      try {
        resAction = ActionUtil.getEmptyAction(actionVersion, actionType, shouldEndSession);
        resResponseContent.setAction(resAction);
      } catch (ProtocolException e) {
        e.printStackTrace();
      }
      resResponse.setResponse(resResponseContent);
      return resResponse;
    }

    /**
     * 构造一个空的响应.
     */
    public ResResponse createEmpty() {
      response.getSession().setAttributes(attributes);
      List<ResDirective> directives = new ArrayList<>();
      response.getResponse().getAction().setDirectives(directives);
      response.getResponse().getAction().setShouldEndSession(shouldEndSession);
      response.getResponse().getAction().setType(actionType);
      return response;
    }
  }
}
