package crazycar.persistent.communication;

import java.util.HashSet;

import com.gigaspaces.async.AsyncFutureListener;
import com.gigaspaces.async.AsyncResult;

public class CarAsyncListener implements AsyncFutureListener<HashSet<Integer>> {

	/*the result of an MoveCarTask is the not used because the relevant information
	 * is written into the tuplespace
	 */
	@Override
    public void onResult(AsyncResult<HashSet<Integer>> result) {
         System.out.println("Listener received result");
         
    }

	

}
