package salud.isaConCadenadeMando;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Physiotherapies extends ElementoCadenaMando{
	
	private static final String PHYSIO_TAGNAME = "physiotherapies";
	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGE_FIELD_TAGNAME = "image";

	private static final String FIELD_SEPARATOR = ";";
	public Physiotherapies(ElementoCadenaMando s) {
		super(s);
	}
	
	public StringBuffer read(String name, JsonReader reader) throws IOException {
		StringBuffer resolver = new StringBuffer();
		if (name.equals(PHYSIO_TAGNAME)) {
			
			StringBuffer physiotherapiesData = new StringBuffer();
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				physiotherapiesData.append(readPhysiotherapiesEntry(reader)).append("\n");
				reader.endObject();
			}
			physiotherapiesData.append("\n");
			reader.endArray();
			return physiotherapiesData;
		
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
	private String readPhysiotherapiesEntry(JsonReader reader) throws IOException {
		String phyname = null;
		String phyimage= null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				phyname = reader.nextString();
			} else if (name.equals(IMAGE_FIELD_TAGNAME)) {
				phyimage = reader.nextString();
			} else {
				reader.skipValue();
			}

		}
		return phyname + FIELD_SEPARATOR + phyimage;
	}


}
