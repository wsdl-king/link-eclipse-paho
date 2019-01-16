package com.qws.link.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.sift.AbstractDiscriminator;
import ch.qos.logback.core.util.OptionHelper;
import org.slf4j.Marker;


/**
 * note:比如我现在有这样一个需求,我根据vin输出日志到指定的文件，所以呢，所以我现在就开始这个类了。
 * */
public class TSPDiscriminator extends AbstractDiscriminator<ILoggingEvent> {

	private String key;
	private String defaultValue;

	@Override
	public String getDiscriminatingValue(ILoggingEvent e) {
		Marker marker = e.getMarker();
		if(marker!=null){
			return marker.getName();
		}
		// TODO Auto-generated method stub
		return defaultValue;
	}

	@Override
	public void start() {
		int errors = 0;
		if (OptionHelper.isEmpty(key)) {
			errors++;
			addError("The \"Key\" property must be set");
		}
		if (OptionHelper.isEmpty(defaultValue)) {
			errors++;
			addError("The \"DefaultValue\" property must be set");
		}
		if (errors == 0) {
			started = true;
		}
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return
	 * @see #setDefaultValue(String)
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * The default MDC value in case the MDC is not set for
	 * {@link #setKey(String) mdcKey}.
	 * <p/>
	 * <p>
	 * For example, if {@link #setKey(String) Key} is set to the value
	 * "someKey", and the MDC is not set for "someKey", then this appender will
	 * use the default value, which you can set with the help of this method.
	 *
	 * @param defaultValue
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}
