package com.neds.cartrackmobilechallange.infrastructure;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import timber.log.Timber;

/**
 * Logging abstraction which allows for adding custom loggers as needed.
 * Included is a file-based logger as well as a debug logger.
 */
public class Lg {

    private static class DebugTreeCollector extends Timber.DebugTree {
        private LogCollector logCollector;

        DebugTreeCollector(LogCollector logCollector) {
            this.logCollector = logCollector;
        }

        @Override
        protected void log(final int priority, final String tag, @NotNull final String message, final Throwable t) {
            logCollector.log(new LogEntry() {
                @Override
                public int priority() {
                    return priority;
                }

                @Override
                public String tag() {
                    return tag;
                }

                @Override
                public String message() {
                    return message;
                }

                @Override
                public Throwable throwable() {
                    return t;
                }

                @Override
                public Date date() {
                    return new Date();
                }
            });
        }
    }

    public interface LogCollector {
        void log(LogEntry entry);
    }

    public static class FileLogCollector implements LogCollector {
        private final String LOG_TAG = FileLogCollector.class.getSimpleName();
        private final String directory;
        private final Timer ruleCop;
        private String applicationId;
        private int maxDays;

        public FileLogCollector(String directory, String applicationId, int maxDays) {
            this.directory = directory;
            this.applicationId = applicationId;
            this.maxDays = maxDays;

            ruleCop = new Timer("File Log Rule Cop");
            ruleCop.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    checkFileRules();
                }
            }, 15000, 15000);
        }

        @SuppressLint("LogNotTimber")
        @Override
        public void log(LogEntry entry) {
            try {
                String fileNameTimeStamp = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(entry.date());
                String logTimeStamp = new SimpleDateFormat("E MMM dd yyyy 'at' hh:mm:ss:SSS aaa", Locale.getDefault()).format(entry.date());
                String fileName = fileNameTimeStamp + ".log";

                // Create file
                File file = generateFile(fileName);

                // If file created or exists save logs
                if (file != null) {
                    FileWriter writer = new FileWriter(file, true);
                    writer.append(logTimeStamp);
                    writer.append("\t");
                    writer.append(String.valueOf(entry.priority()));
                    writer.append("\t");
                    writer.append(entry.tag());
                    writer.append("\t");
                    writer.append(entry.message());
                    writer.append("\t");
                    if (entry.throwable() != null) {
                        writer.append(entry.throwable().toString());
                        writer.append("\t");
                    }
                    writer.flush();
                    writer.close();
                }
            } catch (Exception e) {
                Log.e(LOG_TAG, "Error while logging into file : " + e);
            }
        }

        private void checkFileRules() {
            String[] files = files();
            if (files == null)
                return;
            if (files.length > maxDays) {
                Arrays.sort(files);
                for (int n = files.length; n < maxDays; n--) {
                    File file = new File(files[n]);
                    file.delete();
                }
            }
        }

        public String[] files() {
            return getRootLogsDirectory()
                    .list((dir, name) -> name.endsWith(".log"));
        }

        private File getRootLogsDirectory() {
            return new File(Environment.getExternalStorageDirectory().getAbsolutePath(), applicationId + File.separator + "Logs");
        }

        /*  Helper method to create file*/
        @Nullable
        private File generateFile(@NonNull String fileName) {
            File file = null;
            if (isExternalStorageAvailable()) {
                File root = getRootLogsDirectory();

                boolean dirExists = true;

                if (!root.exists()) {
                    dirExists = root.mkdirs();
                }

                if (dirExists) {
                    file = new File(root, fileName);
                }
            }
            return file;
        }

        /* Helper method to determine if external storage is available*/
        private boolean isExternalStorageAvailable() {
            return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        }
    }

    public interface LogEntry {
        int priority();

        String tag();

        String message();

        Throwable throwable();

        Date date();
    }

    public static void addLogger(LogCollector logCollector) {
        Timber.plant(new DebugTreeCollector(logCollector));
    }

    public static boolean isDebugLoggerAdded = false;

    public static void addDebugLogger() {
        if (!isDebugLoggerAdded) {
            Timber.plant(new Timber.DebugTree());
            isDebugLoggerAdded = true;
        }
    }

    public static void e(String message) {
        Timber.tag(getTag()).e(message);
    }

    public static void e(String message, Object... args) {
        Timber.tag(getTag()).e(message, args);
    }

    public static void e(Throwable th, String message, Object... args) {
        Timber.tag(getTag()).e(th, message, args);
    }

    public static void e(Throwable th) {
        Timber.tag(getTag()).e(th);
    }

    public static void e(Throwable th, String message) {
        Timber.tag(getTag()).e(th, message);
    }

    public static void e(String message, Throwable th) {
        Timber.tag(getTag()).e(th, message);
    }

    public static void i(String message) {
        Timber.tag(getTag()).i(message);
    }

    public static void i(String message, Object... args) {
        Timber.tag(getTag()).i(message, args);
    }

    public static void i(Throwable th, String message, Object... args) {
        Timber.tag(getTag()).i(th, message, args);
    }

    public static void w(String message) {
        Timber.tag(getTag()).w(message);
    }

    public static void w(String message, Object... args) {
        Timber.tag(getTag()).w(message, args);
    }

    public static void w(Throwable th, String message, Object... args) {
        Timber.tag(getTag()).w(th, message, args);
    }

    public static void d(String message) {
        Timber.tag(getTag()).d(message);
    }

    public static void d(String message, Object... args) {
        Timber.tag(getTag()).d(message, args);
    }

    public static void d(Throwable th, String message, Object... args) {
        Timber.tag(getTag()).d(th, message, args);
    }

    public static void v(String message) {
        Timber.tag(getTag()).v(message);
    }

    public static void v(String message, Object... args) {
        Timber.tag(getTag()).v(message, args);
    }

    public static void v(Throwable th, String message, Object... args) {
        Timber.tag(getTag()).v(th, message, args);
    }

    private static String getTag() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        for (StackTraceElement element :
                stackTrace) {
            if (!element.getClassName().equals(Lg.class.getName())) {
                String className = element.getClassName();
                String[] slitted = className.split("\\.");
                if (slitted.length > 0)
                    return slitted[slitted.length - 1];
                else
                    return className;
            }
        }
        return "";
    }

}