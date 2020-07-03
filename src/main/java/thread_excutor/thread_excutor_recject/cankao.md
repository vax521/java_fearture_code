Executors类也提供了其他方法来创建ThreadPoolExecutor对象。
• newCachedThreadPool()：该方法返回一个ExecutorService对象，因此它可以转换成ThreadExecutor对象并获取其全部方法。需要的话，可以在它的缓存线程池里面创建新的线程来执行新任务。
另外，它会其重用现存且已经执行完当前任务的线程。
• newSingleThreadExecutor()：这是一个比较特别的大小固定的线程执行器。
它创建了只包含一个可用线程的执行器，因此同一时间它只能执行一个任务。
ThreadPoolExecutor类提供了许多方法来获取它的状态信息。
本案例用到了getPoolSize()、getActiveCount()和getCompletedTaskCount()方法来获取线程池的大小、线程数量和已完成的任务数等信息。
也可以用getLargestPoolSize()方法来返回一段时间内线程池中的最大线程数。