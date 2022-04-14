package hello.jdbc.util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MyTxManager implements TxManager {

    private final MyTxSyncManager syncManager;

    @Override
    public void begin() {

    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }
}
