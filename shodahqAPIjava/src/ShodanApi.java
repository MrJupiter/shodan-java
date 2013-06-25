import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author anant
 *
 */
public class ShodanApi {

	/**
	 * 
	 */
	String api_key;
	String base_url="http://www.shodanhq.com/api/";
	
	
	/**
	 * @return
	 */
	public String getBase_url() {
		return base_url;
	}
	public String getApi_key() {
		return api_key;
	}
	/**
	 * @param api_key
	 */
	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	public ShodanApi() {
		// TODO Auto-generated constructor stub
	}

	public ShodanApi(String key) {
		// TODO Auto-generated constructor stub
		setApi_key(key);
	}
	/**
	 * @param hostname
	 * @return
	 */
	private String get_ip(String hostname){
		InetAddress i;
		try {
			i = InetAddress.getByName(hostname);
			return i.getHostAddress();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * @param url
	 * @return
	 */
	private String get_response(String url){
		//response would be a string in json format
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(new URL(url).openStream(),Charset.forName("UTF-8")));
			//read rd to lines and send lines across
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = rd.readLine()) != null) {
				sb.append(line + "\n");
			}
			return sb.toString();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	
	/**
	 * @param u
	 * @return
	 */
	public JSONObject host(URL u){
		//return u.getHost();
		return this.host(this.get_ip(u.getHost()));
	}
	/**
	 * @param ip
	 * @return
	 */
	public JSONObject host(String ip){
		String url = this.base_url + "host?ip=" + ip + "&key=" + this.getApi_key();
		String resp = get_response(url);
		JSONParser parser=new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(resp);
			return obj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @param query
	 * @return
	 */
	public JSONObject search(String query){
		String url = this.base_url + "search?q=" + query + "&key=" + this.getApi_key();
		String resp = get_response(url);
		JSONParser parser=new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * @param query
	 * @return
	 */
	public JSONObject fingerprint(String query){
		// check with php class about its usage and how to replicate it here
		String url = this.base_url + "fingerprint?banner=" + query + "&key=" + this.getApi_key();
		String resp = get_response(url);
		JSONParser parser=new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * @param query
	 * @return
	 */
	public JSONObject search_exploits(String query){
		// check with php class about its usage and how to replicate it here
		String url = this.base_url + "search_exploits?q=" + query + "&key=" + this.getApi_key();
		String resp = get_response(url);
		JSONParser parser=new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * @param query
	 * @return
	 */
	public JSONObject ebdsearch(String query){
		// check with php class about its usage and how to replicate it here
		String url = this.base_url + "exploitdb/search?q=" + query + "&key=" + this.getApi_key();
		String resp = get_response(url);
		JSONParser parser=new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * @param query
	 * @return
	 */
	public JSONObject msfsearch(String query){
		// check with php class about its usage and how to replicate it here
		String url = this.base_url + "msf/search?q=" + query + "&key=" + this.getApi_key();
		String resp = get_response(url);
		JSONParser parser=new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

}
