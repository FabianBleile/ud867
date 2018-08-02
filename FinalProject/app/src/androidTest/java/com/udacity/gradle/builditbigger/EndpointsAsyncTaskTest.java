package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;
import android.text.TextUtils;

import junit.framework.TestCase;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class EndpointsAsyncTaskTest extends AndroidTestCase{

    String mOutput = null;
    CountDownLatch signal =  new CountDownLatch(1);

    public void testAlbumGetTask() throws InterruptedException {

        EndpointsAsyncTask task = new EndpointsAsyncTask(new EndpointsAsyncTask.AsyncResponse() {
            @Override
            public void onProcessFinish(String output) {
                mOutput = output;
                signal.countDown();
            }
        });
        task.execute();
        signal.await();

        assertFalse(TextUtils.isEmpty(mOutput));

    }

}