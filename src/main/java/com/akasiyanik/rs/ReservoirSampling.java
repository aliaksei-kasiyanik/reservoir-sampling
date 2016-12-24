package com.akasiyanik.rs;

import java.util.List;

/**
 * @author akasiyanik
 *         12/23/16
 */
public interface ReservoirSampling<T> {

    void pushItem(T item);

    List<T> getSample();

}
