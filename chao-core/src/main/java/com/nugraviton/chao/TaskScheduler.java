package com.nugraviton.chao;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * Task scheduler interface that abstracts the scheduling of
 * {@link Runnable Runnables} based on different kinds of triggers.
 *
 * <p>This interface is separate from SchedulingTaskExecutor since it
 * usually represents for a different kind of back end, i.e. a thread pool with
 * different characteristics and capabilities. Implementations may implement
 * both interfaces if they can handle both kinds of execution characteristics.
 
 *
 * <p>This interface is roughly equivalent to a JSR-236
 * {@code ManagedScheduledExecutorService} as supported in Java EE 6
 * environments. However, at the time of the Spring 3.0 release, the
 * JSR-236 interfaces have not been released in official form yet.
 *
 * @author Juergen Hoeller
 * @since 3.0
 */
public interface TaskScheduler {

	/**
	 * Schedule the given {@link Runnable}, invoking it whenever the trigger
	 * indicates a next execution time.
	 * <p>Execution will end once the scheduler shuts down or the returned
	 * {@link ScheduledFuture} gets cancelled.
	 * @param task the Runnable to execute whenever the trigger fires
	 * @param trigger an implementation of the {@link Trigger} interface,
	 * e.g. a {@link com.nugraviton.chao.schedule.CronTrigger} object
	 * wrapping a cron expression
	 * @return a {@link ScheduledFuture} representing pending completion of the task,
	 * or {@code null} if the given Trigger object never fires (i.e. returns
	 * {@code null} from {@link Trigger#nextExecutionTime})

	 */
	ScheduledFuture<?> schedule(Runnable task, Trigger trigger);

	/**
	 * Schedule the given {@link Runnable}, invoking it at the specified execution time.
	 * <p>Execution will end once the scheduler shuts down or the returned
	 * {@link ScheduledFuture} gets cancelled.
	 * @param task the Runnable to execute whenever the trigger fires
	 * @param startTime the desired execution time for the task
	 * (if this is in the past, the task will be executed immediately, i.e. as soon as possible)
	 * @return a {@link ScheduledFuture} representing pending completion of the task
	 */
	ScheduledFuture<?> schedule(Runnable task, Date startTime);

	/**
	 * Schedule the given {@link Runnable}, invoking it at the specified execution time
	 * and subsequently with the given period.
	 * <p>Execution will end once the scheduler shuts down or the returned
	 * {@link ScheduledFuture} gets cancelled.
	 * @param task the Runnable to execute whenever the trigger fires
	 * @param startTime the desired first execution time for the task
	 * (if this is in the past, the task will be executed immediately, i.e. as soon as possible)
	 * @param period the interval between successive executions of the task (in milliseconds)
	 * @return a {@link ScheduledFuture} representing pending completion of the task
	 */
	ScheduledFuture<?> scheduleAtFixedRate(Runnable task, Date startTime, long period);

	/**
	 * Schedule the given {@link Runnable}, starting as soon as possible and
	 * invoking it with the given period.
	 * <p>Execution will end once the scheduler shuts down or the returned
	 * {@link ScheduledFuture} gets cancelled.
	 * @param task the Runnable to execute whenever the trigger fires
	 * @param period the interval between successive executions of the task (in milliseconds)
	 * @return a {@link ScheduledFuture} representing pending completion of the task
	 */
	ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long period);

	/**
	 * Schedule the given {@link Runnable}, invoking it at the specified execution time
	 * and subsequently with the given delay between the completion of one execution
	 * and the start of the next.
	 * <p>Execution will end once the scheduler shuts down or the returned
	 * {@link ScheduledFuture} gets cancelled.
	 * @param task the Runnable to execute whenever the trigger fires
	 * @param startTime the desired first execution time for the task
	 * (if this is in the past, the task will be executed immediately, i.e. as soon as possible)
	 * @param delay the delay between the completion of one execution and the start
	 * of the next (in milliseconds)
	 * @return a {@link ScheduledFuture} representing pending completion of the task
	 */
	ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, Date startTime, long delay);

	/**
	 * Schedule the given {@link Runnable}, starting as soon as possible and
	 * invoking it with the given delay between the completion of one execution
	 * and the start of the next.
	 * <p>Execution will end once the scheduler shuts down or the returned
	 * {@link ScheduledFuture} gets cancelled.
	 * @param task the Runnable to execute whenever the trigger fires
	 * @param delay the interval between successive executions of the task (in milliseconds)
	 * @return a {@link ScheduledFuture} representing pending completion of the task
	 */
	ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long delay);
	
	 /**
     * Executes the given command at some time in the future.  The command
     * may execute in a new thread, in a pooled thread, or in the calling
     * thread, at the discretion of the {@code Executor} implementation.
     *
     * @param command the runnable task
     */
    void execute(Runnable command);
    
    void shutdown();
    
    void shutdownNow();

}
