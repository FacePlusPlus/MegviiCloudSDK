package com.megvii.cloud.http;

import android.text.TextUtils;

import java.util.HashMap;


public class FaceOperate {

    private String apiKey = "";
    private String apiSecret = "";

    public FaceOperate(String apiKey, String apiSecret){
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    /**
     * 通过传入在Detect API检测出的人脸标识face_token，分析得出人脸的五官关键点，人脸属性和人脸质量判断信息。最多支持分析5个人脸。
     * @param faceTokens 一个字符串，由一个或多个人脸标识组成，用逗号分隔。最多支持5个face_token。
     * @param returnLandmark 是否检测并返回人脸五官和轮廓的关键点。
     *                       1:检测
     *                       0:不检测
     *                       注：默认值为0
     * @param returnAttributes 是否检测并返回根据人脸特征判断出的年龄，性别，微笑等属性，需要将需要检测的属性组织成一个用逗号分隔的字符串。目前支持：gender, age, smiling, glass, pose 顺序没有要求。默认值为 none ，表示不检测属性。
     * @param returnFaceQuality 是否检测并返回人脸质量判断结果和阈值。人脸质量是指图像中的人脸是否适合进行人脸比对，出现模糊、过亮、过暗、大侧脸、不完整等情况会影响人脸质量分数
     *                           1:检测
     *                           0:不检测
     *                           注：默认值为0
     * @return 返回组果的byte数组
     * @throws Exception
     */
    public byte[] analyzeFace(String faceTokens, int returnLandmark, String returnAttributes, String returnFaceQuality) throws Exception {
        String url = Key.WEB_BASE + Key.SPLIT + Key.FACE + Key.SPLIT + Key.ANALYZE;
        HashMap<String, String> map = new HashMap<>();
        map.put(Key.KEY_FOR_APIKEY, apiKey);
        map.put(Key.KEY_FOR_APISECRET, apiSecret);
        map.put(Key.KEY_FOR_FACE_TOKENS, faceTokens);
        map.put(Key.KEY_FOR_RETURN_LANDMARK, String.valueOf(returnLandmark));
        if(!TextUtils.isEmpty(returnAttributes)){
            map.put(Key.KEY_FOR_RETURN_ATTRIBUTES, returnAttributes);
        }
        if(!TextUtils.isEmpty(returnFaceQuality)){
            map.put(Key.KEY_FOR_RETURN_FACE_QUALITY, returnFaceQuality);
        }
        return HttpRequest.post(url, map);
    }

    /**
     * 通过传入在Detect API检测出的人脸标识face_token，获取一个人脸的关联信息，包括源图片ID、归属的FaceSet。
     * @param faceToken 人脸标识face_token
     * @return 返回组果的byte数组
     * @throws Exception
     */
    public byte[] faceGetDetail(String faceToken) throws Exception {
        String url = Key.WEB_BASE + Key.SPLIT + Key.FACE + Key.SPLIT + Key.ANALYZE;
        HashMap<String, String> map = new HashMap<>();
        map.put(Key.KEY_FOR_APIKEY, apiKey);
        map.put(Key.KEY_FOR_APISECRET, apiSecret);
        map.put(Key.KEY_FOR_FACE_TOKEN, faceToken);
        return HttpRequest.post(url, map);
    }

    /**
     *  为检测出的某一个人脸添加标识信息，该信息会在Search接口结果中返回，用来确定用户身份。
     * @param faceToken 人脸标识face_token
     * @param userId 用户自定义的user_id，不超过255个字符，不能包括^@,&=*'"
     *               建议将同一个人的多个face_token设置同样的user_id。
     * @return 返回组果的byte数组
     * @throws Exception
     */
    public byte[] faceSetUserId(String faceToken, String userId) throws Exception {
        String url = Key.WEB_BASE + Key.SPLIT + Key.FACE + Key.SPLIT + Key.SET_USERID;
        HashMap<String, String> map = new HashMap<>();
        map.put(Key.KEY_FOR_APIKEY, apiKey);
        map.put(Key.KEY_FOR_APISECRET, apiSecret);
        map.put(Key.KEY_FOR_FACE_TOKEN, faceToken);
        map.put(Key.KEY_FOR_USER_ID, userId);
        return HttpRequest.post(url, map);
    }



}
