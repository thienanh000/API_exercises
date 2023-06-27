package utilities.api_helpers;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestHelpers {

	private static String userId;
	private static String token;
	private static RequestSpecification request = given();
	private static Response response;

	// How to call the exactly name for this utility
	private void headerCapapilities() {
		request.header(new Header("Content-type", "application/json; charset=UTF-8"));
		request.header(new Header("Authorization", "Bearer " + token));
	}

	private void getUserIdAndToken() {
		String loginPath = "https://demoqa.com/Account/v1/Login";
		String loginBodyStr = "{\r\n" + "  \"userName\": \"userName527\",\r\n" + "  \"password\": \"Password@123\"\r\n"
				+ "}";
		RequestSpecification request = given();
		request.header(new Header("Content-Type", "application/json"));

		Response loginResponse = request.body(loginBodyStr).post(loginPath);
		Map<String, String> userResponse = JsonPath.from(loginResponse.body().asString()).get();
		userId = userResponse.get("userId");
		token = userResponse.get("token");
	}

	public Response sendPOSTRequest(String postPath, String isbn) {
		getUserIdAndToken();
		String bodyRequest = "{\r\n" + "  \"userId\": \"" + userId + "\",\r\n" + "  \"collectionOfIsbns\": [\r\n"
				+ "    {\r\n" + "      \"isbn\": \"" + isbn + "\"\r\n" + "    }\r\n" + "  ]\r\n" + "}";

		headerCapapilities();
		response = request.body(bodyRequest).post(postPath);
		return response;
	}

	public void sendDELETERequest(String deletePath, String isbn) {
		getUserIdAndToken();
		String deleteBookBodyStr = "{\r\n" + "  \"isbn\": \"" + isbn + "\",\r\n" + "  \"userId\": \"" + userId
				+ "\"\r\n" + "}";
		headerCapapilities();
		response = request.body(deleteBookBodyStr).delete(deletePath);
	}

	public void verifyAddedBook(Response addBookResponse) {
		Map<String, String> responseStr = JsonPath.from(addBookResponse.asString()).get();
		if (responseStr.get("message") != null) {
			System.out.println(responseStr.get("message"));
		} else {
			System.out.println("[Noti] Add the book to Profile successfully!");
		}
	}
}
