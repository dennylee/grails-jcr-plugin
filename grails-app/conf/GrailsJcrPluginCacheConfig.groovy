// cache configuration used in this plugin
order = 100
def fiveMins = 300

config = {
    cache {
        name "jcrPojoCache"
        maxElementsInMemory 10000
        maxElementsOnDisk 10000
        timeToIdleSeconds fiveMins
        timeToLiveSeconds fiveMins
        eternal false
        diskPersistent false
        memoryStoreEvictionPolicy "LRU"
    }
}