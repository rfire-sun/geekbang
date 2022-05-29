## 启动

```
[root@localhost redis]# pwd
/Users/suncheng/redis
[root@localhost redis]# redis-server redis6379/redis6379cluster.conf 
[root@localhost redis]# redis-server redis6380/redis6380cluster.conf 
[root@localhost redis]# redis-server redis6381/redis6381cluster.conf 
[root@localhost redis]# redis-server redis6382/redis6382cluster.conf 
[root@localhost redis]# redis-server redis6383/redis6383cluster.conf 
[root@localhost redis]# redis-server redis6384/redis6384cluster.conf 
```



## 加入集群

这时候虽然是集群模式，节点之间还没有关系，需要加入集群

```
[root@localhost redis6379]# redis-cli -c -p 6379
127.0.0.1:6379> cluster meet 127.0.0.1 6379
OK
127.0.0.1:6379> cluster meet 127.0.0.1 6380
OK
127.0.0.1:6379> cluster meet 127.0.0.1 6381
OK
127.0.0.1:6379> cluster meet 127.0.0.1 6382
OK
127.0.0.1:6379> cluster meet 127.0.0.1 6383
OK
127.0.0.1:6379> cluster meet 127.0.0.1 6384
OK
```



### 查看节点信息

通过cluster nodes命令查看集群信息，可以看到node_id信息和自己对应的节点信息等（其他我还不太清楚）

```
127.0.0.1:6379> cluster nodes
3ac8b48be02aded05638f7518f1698f2146df22c 127.0.0.1:6380@16380 master - 0 1653828281000 1 connected
fd3ce0a13380190e4c6008f48976b4db89de540d 127.0.0.1:6381@16381 master - 0 1653828282000 0 connected
2c3b273c60f4346cc189986bc79034c99bb5b2f4 127.0.0.1:6382@16382 master - 0 1653828283728 3 connected
e331a5775dc70e733c33dff0bd690871b1ff968b 127.0.0.1:6383@16383 master - 0 1653828282703 4 connected
b9c82e9a31f83455e15445edfcdaa5dc6d3895d3 127.0.0.1:6379@16379 myself,master - 0 1653828282000 2 connected
7d213a9a6b3ec96d93465e711f9af8e8fc7fc26d 127.0.0.1:6384@16384 master - 0 1653828281672 5 connected
```



### 查看相应端口

gossip的加10000端口出现了（集群之间的相连），虽然也不知道gossip是啥，然后就是看起来应该是支持ipV6

```
[root@localhost redis6379]# netstat -lnpt | grep redis
tcp        0      0 127.0.0.1:6381          0.0.0.0:*               LISTEN      13106/redis-server  
tcp        0      0 127.0.0.1:6382          0.0.0.0:*               LISTEN      13113/redis-server  
tcp        0      0 127.0.0.1:6383          0.0.0.0:*               LISTEN      13120/redis-server  
tcp        0      0 127.0.0.1:6384          0.0.0.0:*               LISTEN      13129/redis-server  
tcp        0      0 127.0.0.1:16379         0.0.0.0:*               LISTEN      13090/redis-server  
tcp        0      0 127.0.0.1:16380         0.0.0.0:*               LISTEN      13098/redis-server  
tcp        0      0 127.0.0.1:16381         0.0.0.0:*               LISTEN      13106/redis-server  
tcp        0      0 127.0.0.1:16382         0.0.0.0:*               LISTEN      13113/redis-server  
tcp        0      0 127.0.0.1:16383         0.0.0.0:*               LISTEN      13120/redis-server  
tcp        0      0 127.0.0.1:16384         0.0.0.0:*               LISTEN      13129/redis-server  
tcp        0      0 127.0.0.1:6379          0.0.0.0:*               LISTEN      13090/redis-server  
tcp        0      0 127.0.0.1:6380          0.0.0.0:*               LISTEN      13098/redis-server  
tcp6       0      0 ::1:6381                :::*                    LISTEN      13106/redis-server  
tcp6       0      0 ::1:6382                :::*                    LISTEN      13113/redis-server  
tcp6       0      0 ::1:6383                :::*                    LISTEN      13120/redis-server  
tcp6       0      0 ::1:6384                :::*                    LISTEN      13129/redis-server  
tcp6       0      0 ::1:16379               :::*                    LISTEN      13090/redis-server  
tcp6       0      0 ::1:16380               :::*                    LISTEN      13098/redis-server  
tcp6       0      0 ::1:16381               :::*                    LISTEN      13106/redis-server  
tcp6       0      0 ::1:16382               :::*                    LISTEN      13113/redis-server  
tcp6       0      0 ::1:16383               :::*                    LISTEN      13120/redis-server  
tcp6       0      0 ::1:16384               :::*                    LISTEN      13129/redis-server  
tcp6       0      0 ::1:6379                :::*                    LISTEN      13090/redis-server  
tcp6       0      0 ::1:6380                :::*                    LISTEN      13098/redis-server  
```



## 集群间主从配置

### 1 记录主节点

```
b9c82e9a31f83455e15445edfcdaa5dc6d3895d3 127.0.0.1:6379
3ac8b48be02aded05638f7518f1698f2146df22c 127.0.0.1:6380
fd3ce0a13380190e4c6008f48976b4db89de540d 127.0.0.1:6381
```

### 2 集群间配置主从

```
[root@localhost redis6379]# redis-cli -c -p 6382
127.0.0.1:6382> CLUSTER REPLICATE b9c82e9a31f83455e15445edfcdaa5dc6d3895d3
OK
127.0.0.1:6382> 
[root@localhost redis6379]# redis-cli -c -p 6383
127.0.0.1:6383> CLUSTER REPLICATE 3ac8b48be02aded05638f7518f1698f2146df22c
OK
127.0.0.1:6383> 
[root@localhost redis6379]# redis-cli -c -p 6384
127.0.0.1:6384> CLUSTER REPLICATE fd3ce0a13380190e4c6008f48976b4db89de540d
OK
```

查看主从配置

```
127.0.0.1:6384> CLUSTER NODES
e331a5775dc70e733c33dff0bd690871b1ff968b 127.0.0.1:6383@16383 slave 3ac8b48be02aded05638f7518f1698f2146df22c 0 1653828768124 1 connected
7d213a9a6b3ec96d93465e711f9af8e8fc7fc26d 127.0.0.1:6384@16384 myself,slave fd3ce0a13380190e4c6008f48976b4db89de540d 0 1653828768000 0 connected
b9c82e9a31f83455e15445edfcdaa5dc6d3895d3 127.0.0.1:6379@16379 master - 0 1653828767099 2 connected
2c3b273c60f4346cc189986bc79034c99bb5b2f4 127.0.0.1:6382@16382 slave b9c82e9a31f83455e15445edfcdaa5dc6d3895d3 0 1653828766077 2 connected
3ac8b48be02aded05638f7518f1698f2146df22c 127.0.0.1:6380@16380 master - 0 1653828769144 1 connected
fd3ce0a13380190e4c6008f48976b4db89de540d 127.0.0.1:6381@16381 master - 0 1653828770162 0 connected
```



### 4 分配槽位

> 槽位[0..16383]
> 16384/3=5461..1

分配情况如下

```
127.0.0.1:6379	0 - 5461
127.0.0.1:6380	5461 - 10922
127.0.0.1:6381	10922 - 16383
```

do分配

```
redis-cli -p 6379 cluster addslots {0..5461}
redis-cli -p 6380 cluster addslots {5462..10922}
redis-cli -p 6381 cluster addslots {10923..16383}
```

查看槽位分配

看3个master的最后的几个

```
[root@localhost redis6379]# redis-cli -p 6379
127.0.0.1:6379> cluster nodes
3ac8b48be02aded05638f7518f1698f2146df22c 127.0.0.1:6380@16380 master - 0 1653829136000 1 connected 5462-10922
fd3ce0a13380190e4c6008f48976b4db89de540d 127.0.0.1:6381@16381 master - 0 1653829139000 0 connected 10923-16383
2c3b273c60f4346cc189986bc79034c99bb5b2f4 127.0.0.1:6382@16382 slave b9c82e9a31f83455e15445edfcdaa5dc6d3895d3 0 1653829136314 2 connected
e331a5775dc70e733c33dff0bd690871b1ff968b 127.0.0.1:6383@16383 slave 3ac8b48be02aded05638f7518f1698f2146df22c 0 1653829137342 1 connected
b9c82e9a31f83455e15445edfcdaa5dc6d3895d3 127.0.0.1:6379@16379 myself,master - 0 1653829138000 2 connected 0-5461
7d213a9a6b3ec96d93465e711f9af8e8fc7fc26d 127.0.0.1:6384@16384 slave fd3ce0a13380190e4c6008f48976b4db89de540d 0 1653829139401 0 connected

```



## 测试

### 集群测试

出现重定向，表示正常（当key通过redis的hash计算落到不同的槽的时候会出现重定向）

```
127.0.0.1:6379> set kk vv
OK
127.0.0.1:6379> set lsjlfksj asldjfldskf
OK
127.0.0.1:6379> set 2384u8 sdlfj
(error) MOVED 13896 127.0.0.1:6381
127.0.0.1:6379> set kjsadfljk  lSD
(error) MOVED 8118 127.0.0.1:6380
127.0.0.1:6379> 
```



可以使用-c命令跟随重定向，这样再move的时候会自动move过去；比如：`redis-cli -c -p 6379`

```
[root@localhost redis6379]# redis-cli -c -p 6379
127.0.0.1:6379> set *Ddf dsf
OK
127.0.0.1:6379> set ISDJF898 dsfjsdl
OK
127.0.0.1:6379> set lskjdfk89989 sldajflkds
-> Redirected to slot [11429] located at 127.0.0.1:6381
OK
```



### 集群主节点下线验证

控制台结束6379端口进程(master)

```
13090:M 29 May 2022 06:06:47.186 * Background saving terminated with success
^C13090:signal-handler (1653829750) Received SIGINT scheduling shutdown...
13090:M 29 May 2022 06:09:10.202 # User requested shutdown...
13090:M 29 May 2022 06:09:10.202 * Saving the final RDB snapshot before exiting.
13090:M 29 May 2022 06:09:10.208 * DB saved on disk
13090:M 29 May 2022 06:09:10.208 * Removing the pid file.
13090:M 29 May 2022 06:09:10.208 # Redis is now ready to exit, bye bye...
[root@localhost redis]# 
```

仔细对比一下，发现原来对应6379的slave变成了master

```
e331a5775dc70e733c33dff0bd690871b1ff968b 127.0.0.1:6383@16383 slave 3ac8b48be02aded05638f7518f1698f2146df22c 0 1653830064037 1 connected
7d213a9a6b3ec96d93465e711f9af8e8fc7fc26d 127.0.0.1:6384@16384 slave fd3ce0a13380190e4c6008f48976b4db89de540d 0 1653830065053 0 connected
b9c82e9a31f83455e15445edfcdaa5dc6d3895d3 127.0.0.1:6379@16379 master,fail - 1653829751125 1653829746008 2 disconnected
3ac8b48be02aded05638f7518f1698f2146df22c 127.0.0.1:6380@16380 master - 0 1653830064000 1 connected 5462-10922
fd3ce0a13380190e4c6008f48976b4db89de540d 127.0.0.1:6381@16381 myself,master - 0 1653830064000 0 connected 10923-16383
2c3b273c60f4346cc189986bc79034c99bb5b2f4 127.0.0.1:6382@16382 master - 0 1653830063000 6 connected 0-5461

```

正常读写

```
127.0.0.1:6381> set kk vv
-> Redirected to slot [2589] located at 127.0.0.1:6382
OK
127.0.0.1:6382> get kk
"vv"

```



### 主机重新上线

`root@localhost redis]# redis-server redis6379/redis6379cluster.conf `

可以看到日志有变为slave的信息

```
h the new master with just a partial transfer.
15222:S 29 May 2022 06:16:51.684 # Cluster state changed: ok
15222:S 29 May 2022 06:16:52.701 * Connecting to MASTER 127.0.0.1:6382
```

查看集群信息，可以原来的master(6379)变成了slave

```
127.0.0.1:6382> cluster nodes
2c3b273c60f4346cc189986bc79034c99bb5b2f4 127.0.0.1:6382@16382 myself,master - 0 1653830331000 6 connected 0-5461
e331a5775dc70e733c33dff0bd690871b1ff968b 127.0.0.1:6383@16383 slave 3ac8b48be02aded05638f7518f1698f2146df22c 0 1653830337921 1 connected
fd3ce0a13380190e4c6008f48976b4db89de540d 127.0.0.1:6381@16381 master - 0 1653830336000 0 connected 10923-16383
3ac8b48be02aded05638f7518f1698f2146df22c 127.0.0.1:6380@16380 master - 0 1653830334000 1 connected 5462-10922
7d213a9a6b3ec96d93465e711f9af8e8fc7fc26d 127.0.0.1:6384@16384 slave fd3ce0a13380190e4c6008f48976b4db89de540d 0 1653830336899 0 connected
b9c82e9a31f83455e15445edfcdaa5dc6d3895d3 127.0.0.1:6379@16379 slave 2c3b273c60f4346cc189986bc79034c99bb5b2f4 0 1653830337000 6 connected
```



## PS

当将所有的机器关闭后再重启会还原成关闭之前的状态

这时候找到相应的working directory，将里面的node配置文件删除即可



## 参考

https://www.cnblogs.com/Yunya-Cnblogs/p/14608937.html
