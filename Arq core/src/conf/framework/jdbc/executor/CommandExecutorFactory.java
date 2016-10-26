package conf.framework.jdbc.executor;

public class CommandExecutorFactory {
	

	public static Executor getExecutor() {
		return new JDBCCommandExecutorImpl();
	}

}
