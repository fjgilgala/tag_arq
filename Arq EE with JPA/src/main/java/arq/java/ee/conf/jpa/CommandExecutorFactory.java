package arq.java.ee.conf.jpa;

class CommandExecutorFactory {

	public static Executor getExecutor() {
		return new JpaCommandExecutorImpl();
	}

}
