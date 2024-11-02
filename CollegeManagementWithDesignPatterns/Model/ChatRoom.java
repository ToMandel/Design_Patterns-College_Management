package Model;

import java.util.ArrayList;

class ChatRoom {
	public String broadcast(String lecType, ArrayList<Lecturer> lecArr, String source, String message) {
		String str = "";
		if (lecArr.size() == 0) {
			return "Please add new Lecturers to the college first";
		} else {
			for (Lecturer lec : lecArr) {
				if (lec.getColl().getColName().equals(source)) {
					if (lec.getClass().getSimpleName().equals(lecType)) {
						str += lec.receive(source, message) + "\n";
					}
				}
			}
			if (str.equals("")) {
				return "There are no Lecturers of this type in " + source + " College";
			}
			return str;
		}
	}
}
