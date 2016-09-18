package com.megvii.cloud.http;

import android.text.TextUtils;

import org.w3c.dom.Text;

import java.io.File;
import java.util.HashMap;

/**
 * 这个类里的所有方法都是网络请求，所以请在异步线程中调用
 */
public class CommonOperate {

    private String apiKey = "";
    private String apiSecret = "";

    public CommonOperate(String apiKey, String apiSecret){
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    /**
     * 调用者提供图片文件或者图片URL，进行人脸检测。识别出的人脸会给出人脸标识face_token，用于后续的人脸比对、检测人脸属性和关键点等操作。
     * @param imageUrl 图片链接
     * @return 返回组果的byte数组
     * @throws Exception
     */
    public byte[] detectUrl(String imageUrl) throws Exception {
        String url = Key.WEB_BASE + Key.SPLIT + Key.DETECT;
        HashMap<String, String> map = new HashMap<>();
        map.put(Key.KEY_FOR_APIKEY, apiKey);
        map.put(Key.KEY_FOR_APISECRET, apiSecret);
        map.put(Key.KEY_FOR_IMAGE_URL, imageUrl);
        return HttpRequest.post(url, map);
    }

    /**
     *  调用者提供图片文件或者图片URL，进行人脸检测。识别出的人脸会给出人脸标识face_token，用于后续的人脸比对、检测人脸属性和关键点等操作。
     * @param file 文件
     * @return 返回组果的byte数组
     * @throws Exception
     */
    public byte[] detectFile(File file) throws Exception {
        String url = Key.WEB_BASE + Key.SPLIT + Key.DETECT;
        HashMap<String, String> map = new HashMap<>();
        map.put(Key.KEY_FOR_APIKEY, apiKey);
        map.put(Key.KEY_FOR_APISECRET, apiSecret);
        return HttpRequest.post(url, map, null, file);
    }

    /**
     *  调用者提供图片文件或者图片URL，进行人脸检测。识别出的人脸会给出人脸标识face_token，用于后续的人脸比对、检测人脸属性和关键点等操作。
     * @param fileByte 二进制数组
     * @return 返回组果的byte数组
     * @throws Exception
     */
    public byte[] detectByte(byte[] fileByte) throws Exception {
        String url = Key.WEB_BASE + Key.SPLIT + Key.DETECT;
        HashMap<String, String> map = new HashMap<>();
        map.put(Key.KEY_FOR_APIKEY, apiKey);
        map.put(Key.KEY_FOR_APISECRET, apiSecret);
        return HttpRequest.post(url, map, fileByte, null);
    }

    /**
     * 将两个人脸进行比对，来判断是否为同一个人。需要先使用Detect API检测图片中的人脸，并记录下人脸标识face_token后传入Compare API进行人脸比对。
     * @param faceToken1 第一个人脸标识face_token
     * @param faceToken2 第二个人脸标识face_token
     * @return 返回组果的byte数组
     * @throws Exception
     */
    public byte[] compare(String faceToken1, String faceToken2) throws Exception {
        String url = Key.WEB_BASE + Key.SPLIT + Key.COMPARE;
        HashMap<String, String> map = new HashMap<>();
        map.put(Key.KEY_FOR_APIKEY, apiKey);
        map.put(Key.KEY_FOR_APISECRET, apiSecret);
        map.put(Key.KEY_FOR_FACE_TOKEN1, faceToken1);
        map.put(Key.KEY_FOR_FACE_TOKEN2, faceToken2);
        return HttpRequest.post(url, map);
    }

    /**
     *  在Faceset中找出与目标人脸最相似的一张或多张人脸。
     * @param faceToken 与Faceset中人脸比对的face_token
     * @param faceSetToken Faceset的标识
     * @param returnResultCount 返回比对置信度最高的n个结果，范围[1,5]。默认值为1
     * @return 返回组果的byte数组
     * @throws Exception
     */
    public byte[] searchByFaceSetToken(String faceToken, String faceSetToken, int returnResultCount) throws Exception {
        String url = Key.WEB_BASE + Key.SPLIT + Key.SEARCH;
        HashMap<String, String> map = new HashMap<>();
        map.put(Key.KEY_FOR_APIKEY, apiKey);
        map.put(Key.KEY_FOR_APISECRET, apiSecret);
        map.put(Key.KEY_FOR_FACE_TOKEN, faceToken);
        map.put(Key.KEY_FOR_FACESET_TOKEN, faceSetToken);
        map.put(Key.KEY_FOR_RETURN_RESULT_COUNT, String.valueOf(returnResultCount));
        return HttpRequest.post(url, map);
    }


    /**
     *  在Faceset中找出与目标人脸最相似的一张或多张人脸。
     * @param faceToken 与Faceset中人脸比对的face_token
     * @param outerId 用户自定义的Faceset标识
     * @param returnResultCount 返回比对置信度最高的n个结果，范围[1,5]。默认值为1
     * @return 返回组果的byte数组
     * @throws Exception
     */
    public byte[] searchByOuterId(String faceToken, String outerId, int returnResultCount) throws Exception {
        String url = Key.WEB_BASE + Key.SPLIT + Key.SEARCH;
        HashMap<String, String> map = new HashMap<>();
        map.put(Key.KEY_FOR_APIKEY, apiKey);
        map.put(Key.KEY_FOR_APISECRET, apiSecret);
        map.put(Key.KEY_FOR_FACE_TOKEN, faceToken);
        map.put(Key.KEY_FOR_OUTER_ID, outerId);
        map.put(Key.KEY_FOR_RETURN_RESULT_COUNT, String.valueOf(returnResultCount));
        return HttpRequest.post(url, map);
    }

}
