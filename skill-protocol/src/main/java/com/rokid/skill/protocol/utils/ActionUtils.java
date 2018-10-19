package com.rokid.skill.protocol.utils;

import com.rokid.skill.protocol.exception.ProtocolException;
import com.rokid.skill.protocol.response.response.action.ResAction;
import com.rokid.skill.protocol.response.response.action.directive.confirm.ResConfirm;
import com.rokid.skill.protocol.response.response.action.directive.media.ResMedia;
import com.rokid.skill.protocol.response.response.action.directive.media.mediaitem.ResMediaItem;
import com.rokid.skill.protocol.response.response.action.directive.pickup.ResPickup;
import com.rokid.skill.protocol.response.response.action.directive.voice.ResVoice;
import com.rokid.skill.protocol.response.response.action.directive.voice.item.ResVoiceItem;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wuyukai
 */
public class ActionUtils {

    /**
     * 获取空动作指令
     *
     * @param actionVersion 版本
     * @param actionType 类型
     * @param shouldEndSession 是否结束会话
     * @return 动作指令
     * @throws ProtocolException 协议异常
     */
    public static ResAction getEmptyAction(String actionVersion, String actionType,
        boolean shouldEndSession)
        throws ProtocolException {
        ResAction resAction = new ResAction();
        if (StringUtils.isBlank(actionVersion)) {
            throw new ProtocolException("actionVersion is isBlank");
        }
        resAction.setVersion(actionVersion);
        if (StringUtils.isBlank(actionType)) {
            throw new ProtocolException("actionType is error");
        }
        if (!ResAction.ACTION_TYPE_NORMAL.equals(actionType) &&
            !ResAction.ACTION_TYPE_EXIT.equals(actionType)) {
            throw new ProtocolException("actionType is error");
        }
        resAction.setType(actionType);
        resAction.setShouldEndSession(shouldEndSession);
        return resAction;
    }

    /**
     * 构建Voice节点
     *
     * @param voiceAction 详见 ResVoice.VOICE_ACTION
     * @param disableEvent 是否关闭Event事件
     */
    public static ResVoice getResVoice(String voiceAction, boolean disableEvent, String itemId,
        String voiceContent)
        throws ProtocolException {
        if (checkVoiceAction(voiceAction)) {
            ResVoice resVoice = new ResVoice();
            resVoice.setAction(voiceAction);
            resVoice.setDisableEvent(disableEvent);
            if (ResVoice.VOICE_ACTION_PLAY.equals(voiceAction)) {
                if (StringUtils.isBlank(voiceContent)) {
                    throw new ProtocolException("voiceContent is isBlank");
                }
                ResVoiceItem voiceItem = new ResVoiceItem();
                voiceItem.setItemId(itemId);
                voiceItem.setTts(voiceContent);
                resVoice.setItem(voiceItem);
            }
            return resVoice;
        }
        throw new ProtocolException("voiceAction is error");
    }

    /**
     * 构建Confirm节点
     */
    public static ResConfirm getResConfirm(String confirmIntent, String confirmSlot,
        List<String> optionWords)
        throws ProtocolException {
        if (!StringUtils.isBlank(confirmIntent) && !StringUtils.isBlank(confirmSlot)) {
            ResConfirm resConfirm = new ResConfirm();
            resConfirm.setConfirmIntent(confirmIntent);
            resConfirm.setConfirmSlot(confirmSlot);
            resConfirm.setOptionWords(optionWords);
            return resConfirm;
        } else {
            throw new ProtocolException("confirmIntent or confirmSlot isBlank");
        }
    }

    /**
     * 检查Voice的Action事件
     */
    public static boolean checkVoiceAction(String voiceAction) {
        if (StringUtils.isBlank(voiceAction)) {
            return false;
        }
        return ResVoice.VOICE_ACTION_PLAY.equals(voiceAction) || ResVoice.VOICE_ACTION_PAUSE
            .equals(voiceAction) || ResVoice.VOICE_ACTION_RESUME.equals(voiceAction)
            || ResVoice.VOICE_ACTION_STOP.equals(voiceAction);
    }

    /**
     * 构建 ResMedia节点
     */
    public static ResMedia getResMeida(String mediaAction, boolean disableEvent, String itemId,
        String mediaToken,
        String mediaType, String mediaUrl, long mediaOffset) throws ProtocolException {
        if (checkMediaAction(mediaAction)) {
            ResMedia resMedia = new ResMedia();
            resMedia.setDisableEvent(disableEvent);
            resMedia.setAction(mediaAction);
            if (ResMedia.MEDIA_ACTION_PLAY.equals(mediaAction)) {
                ResMediaItem resMediaItem = new ResMediaItem();
                if (StringUtils.isBlank(mediaUrl)) {
                    throw new ProtocolException("mediaUrl isBlank");
                }
                resMediaItem.setUrl(mediaUrl);
                if (StringUtils.isBlank(mediaType)) {
                    throw new ProtocolException("mediaType is error");
                }
                if (!ResMediaItem.MEDIA_TYPE_AUDIO.equals(mediaType) &&
                    !ResMediaItem.MEDIA_TYPE_VIDEO.equals(mediaType)) {
                    throw new ProtocolException("mediaType is error");
                }
                resMediaItem.setType(mediaType);
                resMediaItem.setToken(mediaToken);
                if (mediaOffset < 0) {
                    throw new ProtocolException("mediaOffset is error");
                }
                resMediaItem.setOffsetInMilliseconds(mediaOffset);
                resMedia.setItem(resMediaItem);
            }
        }
        throw new ProtocolException("Error mediaAction:" + mediaAction);
    }

    /**
     * 检查VoiceAction
     */
    public static boolean checkMediaAction(String mediaAction) {
        if (StringUtils.isBlank(mediaAction)) {
            return false;
        }
        return ResMedia.MEDIA_ACTION_PLAY.equals(mediaAction) || ResMedia.MEDIA_ACTION_PAUSE
            .equals(mediaAction)
            || ResMedia.MEDIA_ACTION_RESUME.equals(mediaAction)
            || ResMedia.MEDIA_ACTION_STOP.equals(mediaAction);
    }

    /**
     * 获取ResPickUp节点
     */
    public static ResPickup getResPickup(boolean enable, long durationInMilliseconds)
        throws ProtocolException {
        ResPickup resPickup = new ResPickup();
        resPickup.setEnable(enable);
        if (durationInMilliseconds < 0) {
            throw new ProtocolException("durationInMilliseconds is error");
        }
        resPickup.setDurationInMilliseconds(durationInMilliseconds);
        return resPickup;
    }

    /**
     * 检查Action类型
     */
    public static boolean checkActionType(String actionType) {
        return ResAction.ACTION_TYPE_EXIT.equals(actionType) || ResAction.ACTION_TYPE_NORMAL
            .equals(actionType);
    }

    /**
     * 检查ResVoice的合法性
     */
    public static boolean checkVoice(ResVoice resVoice) {
        if (resVoice != null) {
            if (!checkVoiceAction(resVoice.getAction())) {
                return false;
            }
            if (ResVoice.VOICE_ACTION_PLAY.equals(resVoice.getAction())) {
                if (resVoice.getItem() == null) {
                    return false;
                }
                return !StringUtils.isBlank(resVoice.getItem().getTts());
            }
        }
        return true;
    }

    /**
     * 检查Media节点的合法性
     */
    public static boolean checkMedia(ResMedia resMedia) {
        if (resMedia != null) {
            if (!checkMediaAction(resMedia.getAction())) {
                return false;
            }
            if (ResMedia.MEDIA_ACTION_PLAY.equals(resMedia.getAction())) {
                if (resMedia.getItem() == null) {
                    return false;
                }
                if (StringUtils.isBlank(resMedia.getItem().getUrl())) {
                    return false;
                }
                return resMedia.getItem().getOffsetInMilliseconds() >= 0;
            }
        }
        return true;
    }

    /**
     * 检查PickUp节点
     */
    public static boolean checkPickup(ResPickup resPickup) {
        if (resPickup != null) {
            return resPickup.getDurationInMilliseconds() >= 0;
        }
        return true;
    }

    /**
     * 检查confirm节点
     */
    public static boolean checkConfirm(ResConfirm resConfirm) {
        if (resConfirm != null) {
            return !StringUtils.isBlank(resConfirm.getConfirmIntent())
                && !StringUtils.isBlank(resConfirm.getConfirmSlot());
        }
        return true;
    }
}
