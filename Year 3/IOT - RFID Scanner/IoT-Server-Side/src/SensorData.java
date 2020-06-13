

public class SensorData {

	int readerserial;
	String tagvalue;
	String valid;
	String roomid;
	String motorid;

	public SensorData(int readerserial, String tagvalue, String valid, String roomid, String motorid) {
		super();
		this.readerserial = readerserial;
		this.tagvalue = tagvalue;
		this.valid = valid;
		this.roomid = roomid;
		this.motorid = motorid;
	}

	public String getMotorid() {
		return motorid;
	}

	public void setMotorid(String motorid) {
		this.motorid = motorid;
	}

	public int getReaderserial() {
		return readerserial;
	}

	public void setReaderserial(int readerserial) {
		this.readerserial = readerserial;
	}

	public String getTagvalue() {
		return tagvalue;
	}

	public void setTagvalue(String tagvalue) {
		this.tagvalue = tagvalue;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getRoomid() {
		return roomid;
	}

	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	@Override
	public String toString() {
		return "SensorData [readerserial=" + readerserial + ", tagvalue=" + tagvalue + ", valid=" + valid + ", roomid="
				+ roomid + ", motorid=" + motorid + "]";
	}

}
