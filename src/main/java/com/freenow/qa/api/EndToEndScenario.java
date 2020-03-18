package com.freenow.qa.api;


import com.aventstack.extentreports.markuputils.Markup;
import com.freenow.qa.pojo.CommentAPIResponse;
import com.freenow.qa.pojo.PostAPIResponse;
import com.freenow.qa.pojo.userAPIpojo.UserAPIResponse;
import com.freenow.qa.util.common.CustomAssertionsUtil;
import com.freenow.qa.util.common.ExtentUtil;
import com.freenow.qa.util.common.LogUtils;
import com.freenow.qa.util.common.TestUtil;
import io.restassured.response.Response;
import org.testng.Assert;

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
    private static ExtentUtil extentUtilInstance = ExtentUtil.getInstance();
    private static CustomAssertionsUtil assertions = CustomAssertionsUtil.getInstance();
    private static String userId;
    private static ArrayList<Integer> postIdList = new ArrayList<Integer>();
    private static ArrayList<String> emailIdList = new ArrayList<String>();
    private static ArrayList<String> validEmailIdList = new ArrayList<String>();
    private static ArrayList<String> invalidEmailIdList = new ArrayList<String>();
    private static UsersAPI usersAPIInstance = UsersAPI.getInstance();
    private static PostsAPI postsAPIInstance = PostsAPI.getInstance();
    private static CommentsAPI commentsAPIInstance = CommentsAPI.getInstance();


    public static void search_for_the_given_user_by_userName(String paramName, String username) {
        try {
            extentUtilInstance.getTest().assignCategory("e2e");
            LOGGER.info("Starting method: search_for_the_given_user_by_userName");
            //Call User API by username = "Samantha"
            Response userResponse = usersAPIInstance.get_User_By_Username("username", username);
            for (UserAPIResponse userAPIResponseInstance : userResponse.as(UserAPIResponse[].class)) {
                userId = userAPIResponseInstance.getId().toString();
            }
        } catch (Exception e) {
            LOGGER.error(e.toString() + e.getStackTrace()[1].toString());
            Assert.fail(e.toString() + e.getStackTrace()[1].toString());
        }
    }


    public static void search_for_the_post_by_userId() {
        try {

            LOGGER.info("Starting method: search_for_the_post_by_userId");
            Response postResponse = postsAPIInstance.get_Post_By_UserId("userId", userId);
            for (PostAPIResponse PostAPIResponseInstance : postResponse.as(PostAPIResponse[].class)) {
                postIdList.add(PostAPIResponseInstance.getId());
            }
            LOGGER.info("List of Post Ids: " + postIdList);
        } catch (Exception e) {
            LOGGER.error(e.toString() + e.getStackTrace()[1].toString());
            Assert.fail(e.toString() + e.getStackTrace()[1].toString());
        }
    }


    public static void fetch_the_comments_by_postId() {
        try {

            LOGGER.info("Starting method: fetch_the_comments_by_postId");
            for (Integer postId : postIdList) {
                Response commentResponse = commentsAPIInstance.get_Comment_By_PostId("postId", postId.toString());
                if (commentResponse.getContentType().equalsIgnoreCase("application/json; charset=utf-8")) {
                    for (CommentAPIResponse commentAPIResponse : commentResponse.as(CommentAPIResponse[].class)) {
                        emailIdList.add(commentAPIResponse.getEmail());
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.toString() + e.getStackTrace()[1].toString());
            Assert.fail(e.toString() + e.getStackTrace()[1].toString());
        }
    }


    public static void validate_emailId_for_each_comment() {
        try {

            LOGGER.info("Starting method: validate_emailId_for_each_comment");
            for (String emailId : emailIdList) {
                if (testUtilInstance.validateEmailId(emailId)) {
                    validEmailIdList.add(emailId);
                } else {
                    invalidEmailIdList.add(emailId);
                }
            }
            assertions.assertTrue(invalidEmailIdList.isEmpty(), "Invalid email id found");
            LOGGER.info("List of all valid Email Ids: ");
            LOGGER.info("" + validEmailIdList);
            LOGGER.info("List of all invalid Email Ids: ");
            LOGGER.info("" + invalidEmailIdList);

        } catch (Exception e) {
            LOGGER.error(e.toString() + e.getStackTrace()[1].toString());
            Assert.fail(e.toString() + e.getStackTrace()[1].toString());
        }
    }
}