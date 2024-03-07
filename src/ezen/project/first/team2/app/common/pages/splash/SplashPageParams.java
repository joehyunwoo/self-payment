////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_011300] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.pages.splash;

import java.io.FileInputStream;
import java.util.Properties;

public class SplashPageParams {
	public static interface Listener {
		public void onConnectingDb(Object param);

		public void onLoadResources(Object param, int resourceIndex, int resourceCount);

		public void onCompleteResources(Object param);
	}

	public final static String DEFAULT_DB_HOST = "localhost";
	public final static int DEFAULT_DB_PORT = 1521;
	public final static String DEFAULT_DB_ID = "self_payment";
	public final static String DEFAULT_DB_PW = "1234";

	private Listener mListener = null;
	private Object mParam = null;
	private int mResourceCount = 0;

	private String mDbHost;
	private int mDbPort;
	private String mDbId;
	private String mDbPw;

	// 생성자
	public SplashPageParams(Listener listener, Object param, int resourceCount,
			String dbHost, int dbPort, String dbId, String dbPw) {
		this.mListener = listener;
		this.mParam = param;
		this.mResourceCount = resourceCount;

		if (dbHost.isEmpty() || dbPort == -1 || dbId.isEmpty() || dbPw.isEmpty()) {
			try (
					FileInputStream fis = new FileInputStream("dbconn.ini")) {

				var props = new Properties();
				props.load(fis);

				dbHost = props.getProperty("host", "?");
				dbPort = Integer.parseInt(props.getProperty("port", "-1"));
				dbId = props.getProperty("id", "?");
				dbPw = props.getProperty("pw", "?");

				if (dbHost.isEmpty())
					dbHost = DEFAULT_DB_HOST;
				if (dbPort == -1)
					dbPort = DEFAULT_DB_PORT;
				if (dbId.isEmpty())
					dbId = DEFAULT_DB_ID;
				if (dbPw.isEmpty())
					dbPw = DEFAULT_DB_PW;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		this.mDbHost = dbHost;
		this.mDbPort = dbPort;
		this.mDbId = dbId;
		this.mDbPw = dbPw;
	}

	public SplashPageParams(Listener listener, Object param, int resourceCount) {
		this(listener, param, resourceCount, "", -1, "", "");
	}

	public Listener getListener() {
		return this.mListener;
	}

	public Object getParam() {
		return this.mParam;
	}

	public int getResurceCount() {
		return this.mResourceCount;
	}

	public String getDbHost() {
		return this.mDbHost;
	}

	public int getDbPort() {
		return this.mDbPort;
	}

	public String getDbId() {
		return this.mDbId;
	}

	public String getDbPw() {
		return this.mDbPw;
	}
}
