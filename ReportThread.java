public class ReportThread extends Thread {
    private FinanceManager financeManager;
    private boolean running = true;

    public ReportThread(FinanceManager fm) {
        this.financeManager = fm;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while (running) {
            synchronized (financeManager.getReportLock()) {
                while (!financeManager.isReportRequested() && running) {
                    try {
                        financeManager.getReportLock().wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (!running) {
                    break;
                }
                // Generate report
                System.out.println("\n--- Generating Background Report ---");
                financeManager.generateReport();
                System.out.println("--- Background Report Complete ---\n");
                financeManager.setReportRequested(false);
            }
            try {
                Thread.sleep(5000); // Wait 5 seconds before checking again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}