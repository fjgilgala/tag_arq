package conf.framework.jpa;

class CommandExecutorFactory {

	public static Executor getExecutor() {
		return new JpaCommandExecutorImpl();
	}

}
