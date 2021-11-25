package sfa.bricolage.v2.global;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class E4ALogger {
	private static eE4ALoggerLevel _envLevel = eE4ALoggerLevel.debug;
	private String _id = null;
	private final SimpleDateFormat sdf = new SimpleDateFormat("[MM-dd_HH:mm:ss]", Locale.US); 
	
	public static void setEnvLevel (String x) {
		if (x == null) {
			 _envLevel = eE4ALoggerLevel.severe;
			 return;
		}
			
		String y = x.toLowerCase();
		switch (y) {
			case "debug" : _envLevel = eE4ALoggerLevel.debug; break;
			case "severe" : _envLevel = eE4ALoggerLevel.severe; break;
			case "info" : _envLevel = eE4ALoggerLevel.info; break; 
			default: _envLevel = eE4ALoggerLevel.severe; break;
		}
	}

	public static eE4ALoggerLevel getEnvLevel () {
		return E4ALogger._envLevel;
	}
	
	private E4ALogger(String Id) {
		_id = Id;
	}
	
	public static E4ALogger getLogger(String Id) {
		E4ALogger x = new E4ALogger (Id);
		return x;
	}


	public void debug(String msg) {
		this._log(eE4ALoggerLevel.debug, msg);
	}

	public void info(String msg) {
		this._log(eE4ALoggerLevel.debug, msg);
	}
	
	public void severe(String msg) {
		this._log(eE4ALoggerLevel.severe, msg);		
	}

	public void dumpFile(eE4ALoggerLevel l, String pathToFile) {
		if (this.isActive(l)) {
			StringBuffer sb = new StringBuffer("Dump of: ");
			sb.append(pathToFile);
			sb.append(System.lineSeparator());

			try {
				BufferedReader r = new BufferedReader(new FileReader(pathToFile));
				String line = r.readLine();
				while (line != null)
				{
					sb.append(line);
					sb.append(System.lineSeparator());

					line = r.readLine();
				}
				r.close();
			}
			catch (Exception e) {
				sb.append(e.getMessage());
			}
			_log(l, sb.toString());
		}
		return;
	}

	public boolean isActive(eE4ALoggerLevel l) {
		boolean retour = false;
		if (E4ALogger._envLevel.isLessOrEqualThan(l)) {
			retour = true;
		}
		return retour;
	}

	private void _log (eE4ALoggerLevel l, String msg) {
		if (this.isActive(l)) {
			StringBuffer sb = new StringBuffer();
			sb.append(sdf.format(new Date()));
			sb.append(String.format("[%c>%c][%s]", l.getCode(), E4ALogger._envLevel.getCode(), _id));
			sb.append(msg);
			System.err.println(sb.toString());
		}
		return;
	}

}
