package com.BibleQuote.async;

import android.util.Log;
import com.BibleQuote.exceptions.BookNotFoundException;
import com.BibleQuote.utils.Task;
import com.BibleQuote.entity.BibleReference;
import com.BibleQuote.exceptions.OpenModuleException;
import com.BibleQuote.managers.Librarian;

public class AsyncOpenChapter extends Task {
	private static final String TAG = "AsyncOpenChapter";

	private Librarian librarian;
	private BibleReference link;
	private Exception exception;
	private Boolean isSuccess;

	public AsyncOpenChapter(String message, Boolean isHidden, Librarian librarian, BibleReference link) {
		super(message, isHidden);
		this.librarian = librarian;
		this.link = link;
	}

	@Override
	protected Boolean doInBackground(String... arg0) {
		isSuccess = false;
		try {
			Log.i(TAG, String.format("Open OSIS link with moduleID=%1$s, bookID=%2$s, chapterNumber=%3$s, verseNumber=%4$s",
					link.getModuleID(), link.getBookID(), link.getChapter(), link.getFromVerse()));

			librarian.openChapter(link);
			isSuccess = true;

		} catch (OpenModuleException e) {
			exception = e;
		} catch (BookNotFoundException e) {
			exception = e;
		}
		return true;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
	}

	public Exception getException() {
		return exception;
	}

	public Boolean isSuccess() {
		return isSuccess;
	}
}
