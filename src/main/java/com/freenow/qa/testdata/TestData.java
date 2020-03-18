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
    private static JsonUtil jsonUtilInstance = JsonUtil.getInstance();

    public static TestData getInstance() {

        if (testDataInstance == null)
            testDataInstance = new TestData();

        return testDataInstance;
    }


    public static List<String> getTestData(Method method) {

        List<String> TestData;

        switch (method.getName()) {
            case "get_User_By_Id":
                TestData = jsonUtilInstance.readTestData("testdata", "User.id");
                break;

            case "get_User_By_User_Name":
            case "executeE2ETest":
                TestData = jsonUtilInstance.readTestData("testdata", "User.username");
                break;

            case "get_Post_By_Id":
                TestData = jsonUtilInstance.readTestData("testdata", "Post.id");
                break;

            case "get_Post_By_UserId":
                TestData = jsonUtilInstance.readTestData("testdata", "Post.userId");
                break;

            case "get_Comment_By_Id":
                TestData = jsonUtilInstance.readTestData("testdata", "Comment.id");
                break;

            case "get_Comment_By_Post_Id":
                TestData = jsonUtilInstance.readTestData("testdata", "Comment.postId");
                break;

            case "get_User_By_Invalid_Id":
            case "get_Post_By_Invalid_Id":
            case "get_Comment_By_Invalid_Id":
                TestData = jsonUtilInstance.readTestData("testdata", "InvalidId.id");
                break;

            default:
                TestData = null;
        }
        return TestData;
    }
}
