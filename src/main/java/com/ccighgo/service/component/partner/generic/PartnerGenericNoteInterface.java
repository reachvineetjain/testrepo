package com.ccighgo.service.component.partner.generic;

import com.ccighgo.service.transport.partner.beans.generic.deletenote.DeleteNote;
import com.ccighgo.service.transport.partner.beans.generic.notes.ScreeNote;
import com.ccighgo.utils.WSDefaultResponse;

public interface PartnerGenericNoteInterface {

	public WSDefaultResponse addNote(ScreeNote note );
	public WSDefaultResponse deleteNote(DeleteNote deleteNote);
}
