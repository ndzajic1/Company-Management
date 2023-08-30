import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Job;
import ba.unsa.etf.rpr.domain.Job;
import ba.unsa.etf.rpr.exceptions.CompanyException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class JobsTest {

        private final JobManager jobManager = Mockito.mock(JobManager.class);
        private final List<Job> jobs = new ArrayList<>();

        @Before
        public void prepareForTesting() throws CompanyException {
            jobs.add(new Job(1,"Salesman", 1000.0, 20000.0));
            jobs.add(new Job(2,"Accountant", 1000.0, 20000.0));
            jobs.add(new Job(3,"Finance Manager", 1000.0, 20000.0));

            // getById setup
            Mockito.doAnswer(args -> {
                for(Job e : jobs){
                    if(e.getId() == (int) args.getArgument(0)) {
                        return e;
                    }
                }
                return null;
            }).when(jobManager).getJobById(Mockito.anyInt());
            // add Job setup
            Mockito.doAnswer(args -> {
                for(Job d : jobs){
                    if(d.getTitle().equals(((Job) args.getArgument(0)).getTitle()))
                        throw new CompanyException("Duplicates");
                }
                jobs.add(args.getArgument(0));
                return null;
            }).when(jobManager).addNewJob(Mockito.any());

        }
        @Test
        public void alljobs() throws CompanyException {
            Mockito.when(jobManager.getAllJobs()).thenReturn(jobs);
            assertEquals(jobs.size(), 3);
            Mockito.doAnswer(args -> {
                Job e = jobManager.getJobById(args.getArgument(0));
                jobs.remove(e);
                return e;
            }).when(jobManager).deleteJob(Mockito.anyInt());
            jobManager.deleteJob(1);
            assertEquals(jobManager.getAllJobs().size(), 2);
        }

        @Test
        public void addValidJob(){
            Job d = new Job(4,"Admin", 1000.0, 20000.0) ;
            int oldSize = jobs.size();
            assertDoesNotThrow(() -> {
                jobManager.addNewJob(d);
            });
            assertEquals(oldSize + 1, jobs.size());
        }

        @Test
        public void addDuplicate(){
            Job d = new Job(4,"Salesman", 1000.0, 20000.0) ;
            int oldSize = jobs.size();
            assertThrows(CompanyException.class, () -> {
                jobManager.addNewJob(d);
            });
            assertEquals(oldSize, jobs.size());

        }
        @Test
         public void invalidSalaryRange() throws CompanyException {
            Mockito.doAnswer(args -> {
                Job e = jobManager.getJobById(((Job) args.getArgument(0)).getId());
                if(e.getMinSalary() > e.getMaxSalary())
                    throw new CompanyException("Invalid salary range");
                jobs.remove(e.getId());
                jobs.add(e);
                return e;
            }).when(jobManager).updateJob(Mockito.any());

            Job j = jobs.get(0);
            j.setTitle("Admin");
            j.setMinSalary(25000.0);
            int size = jobs.size();
            assertThrows(CompanyException.class, () -> {
                jobManager.updateJob(j);
            });
            assertTrue(size == jobs.size());
        }
}
