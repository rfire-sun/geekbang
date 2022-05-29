## 启动节点

```
[root@localhost redis]# pwd
/Users/suncheng/redis
[root@localhost redis]# redis-server redis6379/redis6379.conf 
[root@localhost redis]# redis-server redis6380/redis6380.conf 
[root@localhost redis]# redis-server redis6381/redis6381.conf 
```

## 主从配置

```
[root@localhost ~]# redis-cli -p 6380
127.0.0.1:6380> SLAVEOF 127.0.0.1 6379
OK
127.0.0.1:6380> 
[root@localhost ~]# redis-cli -p 6381
127.0.0.1:6381> SLAVEOF 127.0.0.1 6379
OK
```

## 验证主从节点

```
[root@localhost ~]# redis-cli -p 6379
127.0.0.1:6379> set kk vv
OK
[root@localhost ~]# redis-cli -p 6380
127.0.0.1:6381> get kk
"vv"
127.0.0.1:6381> set kk dd
(error) READONLY You can't write against a read only replica.
```

从节点会同步主节点的key，无法写（set），只能读（get）
