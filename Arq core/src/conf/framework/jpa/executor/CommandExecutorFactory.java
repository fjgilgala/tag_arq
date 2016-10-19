package conf.framework.jpa.executor;

public class CommandExecutorFactory {

	public static Executor getExecutor() {
		return new JpaCommandExecutorImpl();
	}

}
