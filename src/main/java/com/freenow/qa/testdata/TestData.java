package com.freenow.qa.testdata;


import com.freenow.qa.util.file.JsonUtil;
import java.lang.reflect.Method;
import java.util.List;
/***
 * Class to provide test data using TestNG DataProvider
 *It reads data from testData.json located at path src/main/java/com/freenow/qa/testdata/
 * The test data is fetched based on test method name on run-time
 *
 * @author Gaurav Sharma
 */
public class TestData {

    private static TestData testDataInstance = null;
    JsonUtil jsonUtilInstance = JsonUtil.getInstance();

    public static TestData getInstance() {

        if (testDataInstance == null)
            testDataInstance = new TestData();

        return testDataInstance;
    }


    public List<String> getTestData(Method method) {

        List<String> TestData;

        switch (method.getName()) {
            case "getUserById":
                TestData = jsonUtilInstance.readTestData("testdata", "User.id");
                break;

            case "getUserByUserName":
            case "executeE2ETest":
                TestData = jsonUtilInstance.readTestData("testdata", "User.username");
                break;

            case "getPostById":
                TestData = jsonUtilInstance.readTestData("testdata", "Post.id");
                break;

            case "getPostByUserId":
                TestData = jsonUtilInstance.readTestData("testdata", "Post.userId");
                break;

            case "getCommentById":
                TestData = jsonUtilInstance.readTestData("testdata", "Comment.id");
                break;

            case "getCommentByPostId":
                TestData = jsonUtilInstance.readTestData("testdata", "Comment.postId");
                break;

            case "getUserByInvalidId":
            case "getCommentsByInvalidId":
            case "getPostByInvalidId":
                TestData = jsonUtilInstance.readTestData("testdata", "InvalidId.id");
                break;

            default:
                TestData = null;
        }
        return TestData;
    }
}
