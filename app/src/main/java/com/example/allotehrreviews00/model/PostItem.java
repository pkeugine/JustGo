package com.example.allotehrreviews00.model;


/*
포스트 아이템은, 리사이클뷰에 반복되서 들어가야 할 정보를 테이블처럼 설정;
1) 유저가 좋아했는지 안했는지 구별
2) 좋아요 갯수
3) 이미지 URL
등등 함수 이름대로 설정! 되어있슴돵
 */


public class PostItem {

    boolean isUserLike;

    String postLikesCount;
    String postImgUrl;
    String userName;
    String postText;

    public boolean isUserLike() {
        return isUserLike;
    }

    public void setUserLike(boolean userLike) {
        isUserLike = userLike;
    }

    public String getPostLikesCount() {
        return postLikesCount;
    }

    public void setPostLikesCount(String postLikesCount) {
        this.postLikesCount = postLikesCount;
    }

    public String getPostImgUrl() {
        return postImgUrl;
    }

    public void setPostImgUrl(String postImgUrl) {
        this.postImgUrl = postImgUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public PostItem(boolean isUserLike, String postLikesCount, String postImgUrl, String userName, String postText) {
        this.isUserLike = isUserLike;
        this.postLikesCount = postLikesCount;
        this.postImgUrl = postImgUrl;
        this.userName = userName;
        this.postText = postText;
    }
}
