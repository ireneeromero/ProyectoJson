package salud.isaConCadenadeMando;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class ElementoCadenaMando {
	protected ElementoCadenaMando sig;
	
	public ElementoCadenaMando(ElementoCadenaMando s) {
		sig = s;
	}
	
	public StringBuffer read(String name,JsonReader reader) throws IOException {
		return sig.read(name, reader);
	}

}
