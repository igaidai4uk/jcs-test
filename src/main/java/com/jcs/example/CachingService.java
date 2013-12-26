package com.jcs.example;
import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.apache.jcs.admin.CacheRegionInfo;
import org.apache.jcs.admin.JCSAdminBean;
import org.apache.jcs.engine.control.CompositeCache;

import java.io.PrintWriter;
import java.util.LinkedList;

public class CachingService {
    private JCS bookCache;
    private static CachingService instance = new CachingService();

    private CachingService() {
        try {
            this.bookCache = JCS.getInstance("test");
        } catch (CacheException e) {
            e.printStackTrace();
        }
    }

    public static CachingService getInstance() {
        return instance;
    }

    public Integer put(Book book) {
        if (book != null) {
            try {
//                if (this.bookCache.get(book.getIsbn()) != null) {
//                    this.bookCache.remove(book.getIsbn());
//                }
                this.bookCache.put(book.getIsbn(), book);
            } catch (CacheException e) {
                e.printStackTrace();
            }
        }
        return book.getIsbn();
    }

    public Book get(Integer isbn) {
        return (Book) this.bookCache.get(isbn);
    }

    public void remove(Integer isbn) {
        try {
            if (this.bookCache.get(isbn) != null) {
                this.bookCache.remove(isbn);
            }
        } catch (CacheException e) {
            e.printStackTrace();
        }
    }

    public static void showStats(PrintWriter out) {
        JCSAdminBean jcsAdmin = new JCSAdminBean();

        try {
            LinkedList infoItems = jcsAdmin.buildCacheInfo();

            if (infoItems.iterator().hasNext()) {
                CacheRegionInfo info = (CacheRegionInfo) infoItems.iterator().next();
                CompositeCache compCache = info.getCache();

                out.println("Cache Name: " + compCache.getCacheName());
                out.println("Cache Size: " + compCache.getSize());
                out.println("Cache Type: " + compCache.getCacheType());
                out.println("Cache Updates: " + compCache.getUpdateCount());
                out.println("Cache Misses (not found): " + compCache.getMissCountNotFound());
                out.println("Cache Misses (expired): " + compCache.getMissCountExpired());
                out.println("Cache Hits (auxiliary): " + compCache.getHitCountAux());
                out.println("Cache Hits (memory): " + compCache.getHitCountRam());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}