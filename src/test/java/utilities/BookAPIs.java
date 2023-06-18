package utilities;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BookAPIs {

	private static String userId;
	private static String token;

	private void login() {
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

	public void deleteBookfromProfile(String isbn) {
		login();
		String deleteBookPath = "https://demoqa.com/BookStore/v1/Book";
		String deleteBookBodyStr = "{\r\n" + "  \"isbn\": \"" + isbn + "\",\r\n" + "  \"userId\": \"" + userId
				+ "\"\r\n" + "}";
		RequestSpecification deleteRequest = given();
		deleteRequest.header(new Header("Content-type", "application/json; charset=UTF-8"));
		deleteRequest.header(new Header("Authorization", "Bearer " + token));
		Response deleteResponse = deleteRequest.body(deleteBookBodyStr).delete(deleteBookPath);
		deleteResponse.prettyPrint();
	}

	public void addBookToProfile(String isbn) {
		login();
		String addBookPath = "https://demoqa.com/BookStore/v1/Books";
		String bodyRequest = "{\r\n" + "  \"userId\": \"" + userId + "\",\r\n" + "  \"collectionOfIsbns\": [\r\n"
				+ "    {\r\n" + "      \"isbn\": \"" + isbn + "\"\r\n" + "    }\r\n" + "  ]\r\n" + "}";

		RequestSpecification postRequest = given();
		postRequest.header(new Header("Content-Type", "application/json"));
		postRequest.header(new Header("Authorization", "Bearer " + token));
		Response addBookResponse = postRequest.body(bodyRequest).post(addBookPath);

		Map<String, String> responseStr = JsonPath.from(addBookResponse.asString()).get();
		if (responseStr.get("message") != null) {
			System.out.println(responseStr.get("message"));
		} else {
			System.out.println("[Noti] Add the book to Profile successfully!");
		}
	}
}
