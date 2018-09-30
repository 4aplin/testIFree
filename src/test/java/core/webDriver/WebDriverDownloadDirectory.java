package core.webDriver;

public final class WebDriverDownloadDirectory
{
    public static final String DirectoryPath = (new java.io.File("..\\..\\..\\.downloads")).getAbsolutePath();

    public static void Clean()
    {
        for (String file : GetDownloadedFileNames())
        {
            (new java.io.File(file)).delete();
        }
    }

    public static String[] GetDownloadedFileNames()
    {
        return new String[0];
    }

public static void WaitDownloadFiles(int expectedCountFiles, int maximalWaitTime, int sleepInterval) throws InterruptedException {
   long startTicks = java.time.LocalDateTime.now().getSecond();
    while (true) {
        long currentTicks = java.time.LocalDateTime.now().getSecond();
        if (currentTicks - startTicks > maximalWaitTime * 10000L) {
            throw new RuntimeException("Timeout");
        }

        if (GetDownloadedFileNames().length == expectedCountFiles) {
            break;
        }
        Thread.sleep(sleepInterval);
    }
}
}
