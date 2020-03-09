package com.sogou.sgmar.contentprovider;

import android.Manifest;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import org.litepal.LitePal;

public class BookStoreContentProvider extends ContentProvider {
    public static final int BOOK_TABLE = 0;
    public static final int BOOK_ITEM = 1;
    public static final String Authority = "com.sogou.sgmar.contentprovider";
    private static UriMatcher Matcher;

    static {
        Matcher = new UriMatcher(UriMatcher.NO_MATCH);
        Matcher.addURI(Authority, "book", BOOK_TABLE);
        Matcher.addURI(Authority, "book/#", BOOK_ITEM);
    }

    public BookStoreContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        switch (Matcher.match(uri)) {
            case BOOK_TABLE: {
                return "vnd.android.cursor.dir/vnd."+Authority+".book";
            }

            case BOOK_ITEM: {
                return "vnd.android.cursor.item/vnd."+Authority+".book";
            }
        }

        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (Matcher.match(uri)) {
            case BOOK_TABLE: {
                SQLiteDatabase db = LitePal.getDatabase();
                return db.query("book", projection, selection, selectionArgs, null, null, sortOrder);
            }

            case BOOK_ITEM: {
                SQLiteDatabase db = LitePal.getDatabase();
                String id = uri.getPathSegments().get(1);
                return db.query("book", projection, "id = ?", new String[]{id}, null, null, sortOrder);
            }

            default:
                break;
        }

        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
