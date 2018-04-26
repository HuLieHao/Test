import java.security.AccessController;
import java.util.*;

/**
 * User:  maktub
 * Date:   16/5/4 上午9:54
 */
public class ComparatorTest {


    //如果是自定义类,则必须实现compareTo接口
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(9);
        list.add(2);
        list.add(8);
        list.add(4);
        list.add(1);
        list.add(7);
        list.add(3);
        list.add(11);
        list.add(6);
        list.add(10);
        list.add(5);


//        list.sort((o1, o2) -> o1.compareTo(o2));

//        Collections.sort(list, (o1, o2) -> 0);

        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");

        boolean legacy = AccessController.doPrivileged(new sun.security.action.GetBooleanAction("java.util.Arrays.useLegacyMergeSort")).booleanValue();
        System.out.println(legacy);

//        Collections.sort(list);

        Object[] a = list.toArray();
        legacyMergeSort(a);

        ListIterator<Integer> i = list.listIterator();
        for (Object e : a) {
            i.next();
            i.set((Integer) e);
        }

        list.stream().forEach(System.out::println);

    }

    private static void legacyMergeSort(Object[] a) {
        Object[] aux = a.clone();
        mergeSort(aux, a, 0, a.length, 0);
    }

    @SuppressWarnings({"unchecked"})
    private static void mergeSort(Object[] src, Object[] dest, int low, int high, int off) {

        int length = high - low;

        if (length < 7) {
            for (int i = low; i < high; i++)
                for (int j = i; j > low && ((Comparable) dest[j - 1]).compareTo(dest[j]) > 0; j--)
                    swap(dest, j, j - 1);
            return;
        }

        int destLow = low;
        int destHigh = high;
        low += off;
        high += off;
        int mid = (low + high) >>> 1;
        mergeSort(dest, src, low, mid, -off);
        mergeSort(dest, src, mid, high, -off);

        if (((Comparable)src[mid - 1]).compareTo(src[mid]) <= 0) {
            System.arraycopy(src, low, dest, destLow, length);
            return;
        }

        for (int i = destLow, p = low, q = mid; i < destHigh; i++) {
            if (q >= high || p < mid && ((Comparable)src[p]).compareTo(src[q]) <= 0)
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }

    }

    private static void swap(Object[] x, int a, int b) {
        Object t = x[a];
        x[a] = x[b];
        x[b] = t;
    }


}
