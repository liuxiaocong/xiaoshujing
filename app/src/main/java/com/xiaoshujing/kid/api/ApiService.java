package com.xiaoshujing.kid.api;

import com.xiaoshujing.kid.model.BabyInfoBean;
import com.xiaoshujing.kid.model.BabyWishBean;
import com.xiaoshujing.kid.model.BindTokenBean;
import com.xiaoshujing.kid.model.BodyBabyWish;
import com.xiaoshujing.kid.model.BodyBindToken;
import com.xiaoshujing.kid.model.BodyBluetoothData;
import com.xiaoshujing.kid.model.BodyEditBabyInfo;
import com.xiaoshujing.kid.model.BodyGrade;
import com.xiaoshujing.kid.model.BodyMinusCredit;
import com.xiaoshujing.kid.model.BodyMyMoment;
import com.xiaoshujing.kid.model.BodyPractise;
import com.xiaoshujing.kid.model.BodyPractiseUpdate;
import com.xiaoshujing.kid.model.BodySendMsgToParent;
import com.xiaoshujing.kid.model.BodyUpdateMessageStatus;
import com.xiaoshujing.kid.model.BodyUpdateMission;
import com.xiaoshujing.kid.model.CommonRetBean;
import com.xiaoshujing.kid.model.EditBabyInfoBean;
import com.xiaoshujing.kid.model.GetMissionBean;
import com.xiaoshujing.kid.model.GetTokenBean;
import com.xiaoshujing.kid.model.GradeBean;
import com.xiaoshujing.kid.model.MinusCreditBean;
import com.xiaoshujing.kid.model.PostBluetoothBean;
import com.xiaoshujing.kid.model.PostMyMomentBean;
import com.xiaoshujing.kid.model.PractiseEndBean;
import com.xiaoshujing.kid.model.PractiseStartBean;
import com.xiaoshujing.kid.model.PractiseUpdateBean;
import com.xiaoshujing.kid.model.StudyMomentsBean;
import com.xiaoshujing.kid.model.UploadAvatarBean;
import com.xiaoshujing.kid.model.UploadImgBean;
import com.xiaoshujing.kid.model.message.DeleteMessageBean;
import com.xiaoshujing.kid.model.message.GetMessageBean;
import com.xiaoshujing.kid.model.message.SendMsgToParentBean;
import com.xiaoshujing.kid.model.video.GetPaidSeasonBean;
import com.xiaoshujing.kid.model.video.GetSeasonBean;
import com.xiaoshujing.kid.model.video.GetSeasonPaidVideoDetailBean;
import com.xiaoshujing.kid.model.video.GetSeasonVideoDetailBean;
import com.xiaoshujing.kid.model.video.GetSeasonVideoListBean;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by liuxiaocong on 11/8/16.
 */

public interface ApiService {

//    @GET("user/send_code/")
//    Observable<BaseResponse> sendCode(@Query("phone") CharSequence phone, @Query("action") CharSequence action);
//
//    @POST("user/register/")
//    Observable<User> register(@Body HashMap<String, Object> body);

//    @GET("user/request_authorize/")
//    Observable<GetInfoBean> getUserInfoFromUUID(@Query("code") String uuid);

    @GET("user/request_authorize/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<ResponseBody> getUserInfoFromUUID(@Query("code") String uuid);

    @POST("/upload_avatar/")
    @Headers("Accept-Encoding: multipart/form-data")
    Call<UploadAvatarBean> uploadAvatar(@Body MultipartBody filePart);


    @POST("/upload_imgs/")
    Call<UploadImgBean> uploadImgs(@Body MultipartBody filePart);

    @POST("/upload_imgs/")
    Observable<UploadImgBean> uploadImgsV2(@Body MultipartBody filePart);


    @POST("baby/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<EditBabyInfoBean> editBabyInfo(@Body BodyEditBabyInfo editBabyInfo);


    @GET("baby/{bid}")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<BabyInfoBean> getBabyInfo(@Path("bid") String bid);

    @POST("grade/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<GradeBean> getGrade(@Body BodyGrade editBabyInfo);

    @POST("practice/start/")
    @Headers("Content-Type: application/json;")
    Call<PractiseStartBean> startPractise(@Body BodyPractise bodyPractise);

    @GET("practice/end/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<PractiseEndBean> endPractise();

    @GET("practice/restart/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<CommonRetBean> restartPractise();

    @GET("practice/reset/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<CommonRetBean> resetPractise();

    @GET("practice/check/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<CommonRetBean> checkPractise();


    @POST("practice/update/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<PractiseUpdateBean> updatePractise(@Body BodyPractiseUpdate bodyPractiseUpdate);

    @GET("my_moments/")
    @Headers("Content-Type: application/json;")
    Call<StudyMomentsBean> getStudyMoments(@Query("order_by") String order);

    @GET("my_moments/?order_by=-created_at")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<StudyMomentsBean> getStudyMoments();


    @POST("my_moments/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<PostMyMomentBean> postStudyMoments(@Body BodyMyMoment bodyMyMoment);


    @GET("season/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<GetSeasonBean> getSeason(@Query("seasonType") int seasonType);

    @GET("season/{sid}")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<GetSeasonVideoListBean> getSeasonVideoList(@Path("sid") String sid);

    @GET("episode/{vid}")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<GetSeasonVideoDetailBean> getSeasonVideoDetail(@Path("vid") String vid);

    @GET("paid_episode/{vid}")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<GetSeasonPaidVideoDetailBean> getSeasonPaidVideoDetail(@Path("vid") String vid);

    @GET("paid_season/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<GetPaidSeasonBean> getPaidSeasonList();


    //    missionType：任务类型，0为家长添加，1为系统日程任务，2活动任务
//    missionStatus: 任务状态，0为进行中，1为已完成
    @GET("mission/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<GetMissionBean> getMission(@Query("missionType") int misstype, @Query("missionStatus") int missionStatus);

    @GET("mission/{mid}")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<GetMissionBean.MissionBean> getMissionDetail(@Path("mid") String mid);


    @GET("mission/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<GetMissionBean> getMission();

    @GET("mission/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<GetMissionBean> getMission(@Query("missionType") int misstype);

    @GET("mission/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<GetMissionBean> getMissionByStatus(@Query("missionStatus") int missionStatus);

    @POST("mission/update_mission/")
    Call<CommonRetBean> updateMission(@Body BodyUpdateMission bodyUpdateMission);

    @POST("mission/update_mission/")
    Call<CommonRetBean> updateMission();


    @GET("/global_configs/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<ResponseBody> getGlobeSetting();

    @POST("baby_wish/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<BabyWishBean> babyWish(@Body BodyBabyWish bodyBabyWish);

    @POST("user_message/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<SendMsgToParentBean> sendMessageToParent(@Body BodySendMsgToParent bodySendMsgToParent);

    @POST("/device/android/{token}/?udid=uuid&release=appstore")
    @Headers("Content-Type: application/json")
    Call<BindTokenBean> bindToken(@Path("token") String token, @Body BodyBindToken bodyBindToken);

    @DELETE("/device/android/{token}/?udid=uuid")
    @Headers("Content-Type: application/json")
    Call<ResponseBody> unBindToken(@Path("token") String token);

    @GET("/device/android/{token}/?udid=uuid")
    @Headers("Content-Type: application/json")
    Call<GetTokenBean> validBindToken(@Path("token") String token);


    @DELETE("user_message/{mid}/?uuid=uuid&release=appstore")
    @Headers("Content-Type: application/json")
    Call<DeleteMessageBean> deleteMessage(@Path("mid") String mid);

    @GET("user_message/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<GetMessageBean> getUserMessage(@Query("messageType") int messageType, @Query("offset") int offset, @Query("limit") int limit);

    @GET("user_message/?isRead=0")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<GetMessageBean> getUserUnReadMessage(@Query("messageType") int messageType, @Query("offset") int offset, @Query("limit") int limit);

    /**
     * 更改信息已读状态;
     *
     * @param mid
     * @param isRead
     * @return
     */

    @PUT("user_message/{mid}/?uuid=uuid&release=appstore")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<GetMessageBean.MessageBean> updateUserMessageReadStatus(@Path("mid") String mid, @Body BodyUpdateMessageStatus updateMessageStatus);


    //蓝牙硬件api
    @POST("hwdata/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<PostBluetoothBean> postBluetooth(@Body BodyBluetoothData bodyBluetoothData);

    @POST("baby/minus_credit/")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<MinusCreditBean> minusCredit(@Body BodyMinusCredit bodyMinusCredit);

}
