// cache configuration used in this plugin
order = 100
def oneHour = 3600

config = {
    cache {
        name "jcrPojoCache"
        maxElementsInMemory 10000
        maxElementsOnDisk 10000
        timeToIdleSeconds oneHour
        timeToLiveSeconds oneHour
        eternal false
        diskPersistent false
        memoryStoreEvictionPolicy "LRU"
    }
}