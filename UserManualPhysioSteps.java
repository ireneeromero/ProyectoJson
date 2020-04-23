package salud.isaConCadenadeMando;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class UserManualPhysioSteps extends ElementoCadenaMando{	
	private static final String MANUAL_TAGNAME = "userManualPhysioSteps";
	private static final String TITLE_FIELD_TAGNAME = "stepTitle";
	private static final String IMAGE_FIELD_TAGNAME = "stepImage";
	private static final String REF_FIELD_TAGNAME = "physioRef";
	

	private static final String FIELD_SEPARATOR = ";";
	public UserManualPhysioSteps(ElementoCadenaMando s) {
		super(s);	
	}
	public StringBuffer read(String name, JsonReader reader) throws IOException {
		StringBuffer resolver = new StringBuffer();
		if (name.equals(MANUAL_TAGNAME)) {
			
			StringBuffer manualData = new StringBuffer();
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				manualData.append(readManualEntry(reader)).append("\n");
				reader.endObject();
			}
			manualData.append("\n");
			reader.endArray();
			return manualData;
		}
		else {
			if (sig != null) {
				resolver=super.read(name, reader);	
			} else {
					reader.skipValue();
					System.err.println("Category " + name + " not processed.");
			}
		}
		return new StringBuffer(resolver);
		
	}

	// Parses the contents of a rescue medicine presentation entry
	private String readManualEntry(JsonReader reader) throws IOException {
		String title = null;
		String image= null;
		String ref= null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(TITLE_FIELD_TAGNAME)) {
				title = reader.nextString();
			} else if (name.equals(IMAGE_FIELD_TAGNAME)) {
				image = reader.nextString();
			} else if (name.equals(REF_FIELD_TAGNAME)) {
				ref = reader.nextString();
			}else {
				reader.skipValue();
			}

		}
		return title + FIELD_SEPARATOR + image + FIELD_SEPARATOR + ref ;
	}


	

}
