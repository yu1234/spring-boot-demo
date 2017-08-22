package com.yu.springbootdemo.log;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import com.p6spy.engine.spy.appender.StdoutLogger;

/**
 * Created by Administrator on 2016/7/1.
 */
public class SqlLog extends StdoutLogger {
	@Override
	public void logText(String text) {
		super.setStrategy(new MessageFormattingStrategy() {
			public String formatMessage(int i, String s, long l, String s1, String s2, String s3) {
				if (i>=0&&"statement".equals(s1)&&s3 != null && s3.length() > 0) {
					return s1+":"+s3;
				} else {
					return null;
				}

			}
		});
		if (text != null) {
			System.out.println(text);
		}
	}

}
