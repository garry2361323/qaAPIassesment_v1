package com.freenow.qa.api;


import com.freenow.qa.pojo.CommentAPIResponse;
import com.freenow.qa.pojo.PostAPIResponse;
import com.freenow.qa.util.common.LogUtils;
import com.freenow.qa.util.common.TestUtil;
import io.restassured.response.Response;

import java.util.ArrayList;

/***
 * This class executes end to end scenario.
 * Search for the user (Samantha).
 * Use the details fetched to make a search for the posts written by the user.
 * For each post, fetch the comments and validate if the emails in the comment section are in the proper format.
 *
 * @author Gaurav Sharma
 */

public class EndToEndScenario {

    private static LogUtils LOGGER = LogUtils.getInstance(EndToEndScenario.class);
    private static TestUtil testUtilInstance = TestUtil.getInstance();


    public static void getUserByUsernameForE2E(String paramName, String paramValue) {

        //Call User API by username = "Samantha"
        Response userResponse = UsersAPI.getUserByParam(paramName, paramValue);
        ArrayList<Integer> idList = userResponse.path("id");

        //Call Post API by userId
        Response postResponse = PostsAPI.getPostByParam("userId", idList.get(0).toString());

        //Convert Post API response to JAVA Object and iterate through each object instance to get Post id
        for (PostAPIResponse PostAPIResponseInstance : postResponse.as(PostAPIResponse[].class)) {

            //Call Comments API by PostId for each post
            Response commentResponse = CommentsAPI.getCommentByParam("postId", PostAPIResponseInstance.getId().toString());

            //Convert Comments API response to JAVA Object and iterate through each object instance to get email id
            for (CommentAPIResponse CommentAPIResponseInstance : commentResponse.as(CommentAPIResponse[].class)) {

                //Validate Email Id
                testUtilInstance.validateEmailId(CommentAPIResponseInstance.getEmail());
            }
        }

    }
}