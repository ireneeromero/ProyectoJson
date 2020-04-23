package salud.isaConCadenadeMando;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Medicine extends ElementoCadenaMando{
	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String MEDICINES_TAGNAME = "medicines";
	
	public Medicine(ElementoCadenaMando s) {
		super(s);
	}
	
	public StringBuffer read(String name, JsonReader reader) throws IOException{
		StringBuffer resolver = new StringBuffer();
		if (name.equals(MEDICINES_TAGNAME)) {
			StringBuffer medicineData = new StringBuffer();
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				medicineData.append(readMedicinesEntry(reader)).append("\n");
				reader.endObject();
			}
		
			medicineData.append("\n");
			reader.endArray();
		return medicineData;
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
	
	public String readMedicinesEntry(JsonReader reader) throws IOException {
		// reader.require(XmlPullParser.START_TAG, ns, SINGLE_ELEMENT_TAGNAME);
		String medName = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				medName = reader.nextString();
			} else {
				reader.skipValue();
			}
		}

		return medName;
	}
}
