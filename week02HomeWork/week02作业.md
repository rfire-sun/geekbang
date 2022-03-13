# 作业01 案例演示总结
> 1.（选做）使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例。
> 
> 2.（选做）使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。
 
 （必做）根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结，提交到 GitHub。


## 总结
由于刚开始学习，而且前面学的并不扎实，加上还要学习markdown等知识，得到的结论并不深入，但为了表明学习态度，还是得写出能写出的东西，**学的越多，不知道的越多**
1. 使用不同的GC只需要使用不同的参数就行了
1. 使用 -XX:+PrintGCDetails打印详细日志
1. 使用 -XX:+PrintGCDateStamps打印日志时间戳
1. 当使用-XX:+PrintGCDetails打印详细日志时，G1GC打印的日志实在过多，可使用-XX:+PrintGC打印简化的日志
1. 不同的GC打印的日志可能内容不同，但都是与他本身GC的算法关联性强的东西
1. 关于GC这块如果需要讲出内容，大概有各个GC的日志的细节应该怎么读，如果计算确定他是健康、亚健康、病态，新增的速率和上升的速率计算，各个日志为什么会那么打印（进行对比）
1. 使用wrk压测工具对jar进行压测，发现相对于演示的HttpServer1,2,3有数量级的性能的增加
1. 压测发现对于自定义的一个NettyHttpServer性能会有相差
1. windows使用sb进行测试不稳定，可能提早退出，可能性能飘忽不定，出现了HttpServer01 RPS大于Gateway Server的情况
## GC演示
### 串行GC演示
-XX:+UseSerialGC -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps
```

正在执行...
2022-03-13T20:45:40.728+0800: [GC (Allocation Failure) 2022-03-13T20:45:40.728+0800: [DefNew: 139776K->17472K(157248K), 0.0432161 secs] 139776K->44276K(506816K), 0.0433375 secs] [Times: user=0.00 sys=0.03, real=0.04 secs] 
2022-03-13T20:45:40.818+0800: [GC (Allocation Failure) 2022-03-13T20:45:40.818+0800: [DefNew: 157248K->17471K(157248K), 0.0629263 secs] 184052K->87231K(506816K), 0.0630133 secs] [Times: user=0.00 sys=0.08, real=0.06 secs] 
2022-03-13T20:45:40.917+0800: [GC (Allocation Failure) 2022-03-13T20:45:40.917+0800: [DefNew: 157045K->17471K(157248K), 0.0439910 secs] 226805K->131962K(506816K), 0.0440570 secs] [Times: user=0.03 sys=0.02, real=0.04 secs] 
2022-03-13T20:45:40.999+0800: [GC (Allocation Failure) 2022-03-13T20:45:40.999+0800: [DefNew: 157247K->17471K(157248K), 0.0352622 secs] 271738K->177401K(506816K), 0.0353297 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2022-03-13T20:45:41.070+0800: [GC (Allocation Failure) 2022-03-13T20:45:41.070+0800: [DefNew: 157247K->17470K(157248K), 0.0364468 secs] 317177K->219354K(506816K), 0.0365078 secs] [Times: user=0.00 sys=0.03, real=0.04 secs] 
2022-03-13T20:45:41.136+0800: [GC (Allocation Failure) 2022-03-13T20:45:41.136+0800: [DefNew: 157246K->17471K(157248K), 0.0410066 secs] 359130K->265168K(506816K), 0.0410713 secs] [Times: user=0.02 sys=0.02, real=0.04 secs] 
2022-03-13T20:45:41.215+0800: [GC (Allocation Failure) 2022-03-13T20:45:41.215+0800: [DefNew: 157247K->17471K(157248K), 0.0325968 secs] 404944K->305309K(506816K), 0.0326633 secs] [Times: user=0.00 sys=0.03, real=0.03 secs] 
2022-03-13T20:45:41.290+0800: [GC (Allocation Failure) 2022-03-13T20:45:41.290+0800: [DefNew: 157247K->17470K(157248K), 0.0429829 secs] 445085K->348947K(506816K), 0.0430546 secs] [Times: user=0.02 sys=0.01, real=0.04 secs] 
2022-03-13T20:45:41.367+0800: [GC (Allocation Failure) 2022-03-13T20:45:41.367+0800: [DefNew: 157246K->157246K(157248K), 0.0000262 secs]2022-03-13T20:45:41.367+0800: [Tenured: 331476K->283563K(349568K), 0.0424851 secs] 488723K->283563K(506816K), [Metaspace: 3844K->3844K(1056768K)], 0.0426075 secs] [Times: user=0.03 sys=0.00, real=0.04 secs] 
2022-03-13T20:45:41.449+0800: [GC (Allocation Failure) 2022-03-13T20:45:41.449+0800: [DefNew: 139572K->17468K(157248K), 0.0103825 secs] 423135K->334052K(506816K), 0.0104579 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2022-03-13T20:45:41.490+0800: [GC (Allocation Failure) 2022-03-13T20:45:41.490+0800: [DefNew: 157244K->157244K(157248K), 0.0000335 secs]2022-03-13T20:45:41.490+0800: [Tenured: 316584K->307800K(349568K), 0.0506393 secs] 473828K->307800K(506816K), [Metaspace: 3845K->3845K(1056768K)], 0.0507602 secs] [Times: user=0.06 sys=0.00, real=0.05 secs] 
执行结束!共生成对象次数:5797
Heap
 def new generation   total 157248K, used 34656K [0x00000000e0000000, 0x00000000eaaa0000, 0x00000000eaaa0000)
  eden space 139776K,  24% used [0x00000000e0000000, 0x00000000e21d8308, 0x00000000e8880000)
  from space 17472K,   0% used [0x00000000e9990000, 0x00000000e9990000, 0x00000000eaaa0000)
  to   space 17472K,   0% used [0x00000000e8880000, 0x00000000e8880000, 0x00000000e9990000)
 tenured generation   total 349568K, used 307800K [0x00000000eaaa0000, 0x0000000100000000, 0x0000000100000000)
   the space 349568K,  88% used [0x00000000eaaa0000, 0x00000000fd7360c8, 0x00000000fd736200, 0x0000000100000000)
 Metaspace       used 3852K, capacity 4540K, committed 4864K, reserved 1056768K
  class space    used 419K, capacity 428K, committed 512K, reserved 1048576K

Process finished with exit code 0
```

### 并行GC演示
XX:+UseParallelGC -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps
```
正在执行...
2022-03-13T20:48:56.187+0800: [GC (Allocation Failure) [PSYoungGen: 131584K->21501K(153088K)] 131584K->46508K(502784K), 0.0143278 secs] [Times: user=0.05 sys=0.11, real=0.01 secs] 
2022-03-13T20:48:56.242+0800: [GC (Allocation Failure) [PSYoungGen: 153085K->21494K(153088K)] 178092K->89427K(502784K), 0.0215459 secs] [Times: user=0.03 sys=0.11, real=0.02 secs] 
2022-03-13T20:48:56.296+0800: [GC (Allocation Failure) [PSYoungGen: 153051K->21502K(153088K)] 220984K->132697K(502784K), 0.0160328 secs] [Times: user=0.02 sys=0.11, real=0.02 secs] 
2022-03-13T20:48:56.347+0800: [GC (Allocation Failure) [PSYoungGen: 152839K->21503K(153088K)] 264033K->175136K(502784K), 0.0166728 secs] [Times: user=0.05 sys=0.09, real=0.02 secs] 
2022-03-13T20:48:56.400+0800: [GC (Allocation Failure) [PSYoungGen: 152722K->21496K(153088K)] 306356K->214265K(502784K), 0.0149142 secs] [Times: user=0.13 sys=0.03, real=0.01 secs] 
2022-03-13T20:48:56.449+0800: [GC (Allocation Failure) [PSYoungGen: 152974K->21502K(80384K)] 345743K->254400K(430080K), 0.0148581 secs] [Times: user=0.03 sys=0.14, real=0.02 secs] 
2022-03-13T20:48:56.481+0800: [GC (Allocation Failure) [PSYoungGen: 80028K->27612K(116736K)] 312927K->265585K(466432K), 0.0047975 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:48:56.500+0800: [GC (Allocation Failure) [PSYoungGen: 86492K->37963K(116736K)] 324465K->282916K(466432K), 0.0059240 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2022-03-13T20:48:56.525+0800: [GC (Allocation Failure) [PSYoungGen: 96540K->51536K(116736K)] 341493K->302967K(466432K), 0.0073083 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2022-03-13T20:48:56.547+0800: [GC (Allocation Failure) [PSYoungGen: 110416K->39658K(116736K)] 361847K->318919K(466432K), 0.0104001 secs] [Times: user=0.03 sys=0.17, real=0.01 secs] 
2022-03-13T20:48:56.571+0800: [GC (Allocation Failure) [PSYoungGen: 98450K->23398K(116736K)] 377711K->338296K(466432K), 0.0124543 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2022-03-13T20:48:56.583+0800: [Full GC (Ergonomics) [PSYoungGen: 23398K->0K(116736K)] [ParOldGen: 314897K->229231K(349696K)] 338296K->229231K(466432K), [Metaspace: 3845K->3845K(1056768K)], 0.0342502 secs] [Times: user=0.28 sys=0.03, real=0.04 secs] 
2022-03-13T20:48:56.633+0800: [GC (Allocation Failure) [PSYoungGen: 58448K->18763K(116736K)] 287679K->247995K(466432K), 0.0027077 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:48:56.649+0800: [GC (Allocation Failure) [PSYoungGen: 77643K->19394K(116736K)] 306875K->265558K(466432K), 0.0037406 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:48:56.668+0800: [GC (Allocation Failure) [PSYoungGen: 78274K->22717K(116736K)] 324438K->287263K(466432K), 0.0040131 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:48:56.687+0800: [GC (Allocation Failure) [PSYoungGen: 81597K->19239K(116736K)] 346143K->305241K(466432K), 0.0043332 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:48:56.707+0800: [GC (Allocation Failure) [PSYoungGen: 78119K->16704K(116736K)] 364121K->321282K(466432K), 0.0043031 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:48:56.712+0800: [Full GC (Ergonomics) [PSYoungGen: 16704K->0K(116736K)] [ParOldGen: 304577K->258893K(349696K)] 321282K->258893K(466432K), [Metaspace: 3846K->3846K(1056768K)], 0.0308418 secs] [Times: user=0.19 sys=0.00, real=0.03 secs] 
2022-03-13T20:48:56.756+0800: [GC (Allocation Failure) [PSYoungGen: 58880K->17159K(116736K)] 317773K->276052K(466432K), 0.0023515 secs] [Times: user=0.17 sys=0.02, real=0.00 secs] 
2022-03-13T20:48:56.772+0800: [GC (Allocation Failure) [PSYoungGen: 76015K->22052K(116736K)] 334908K->296568K(466432K), 0.0040577 secs] [Times: user=0.09 sys=0.00, real=0.00 secs] 
2022-03-13T20:48:56.791+0800: [GC (Allocation Failure) [PSYoungGen: 80932K->21336K(116736K)] 355448K->316565K(466432K), 0.0040744 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:48:56.811+0800: [GC (Allocation Failure) [PSYoungGen: 80216K->27189K(116736K)] 375445K->342613K(466432K), 0.0049322 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:48:56.816+0800: [Full GC (Ergonomics) [PSYoungGen: 27189K->0K(116736K)] [ParOldGen: 315424K->281971K(349696K)] 342613K->281971K(466432K), [Metaspace: 3846K->3846K(1056768K)], 0.0299390 secs] [Times: user=0.20 sys=0.02, real=0.03 secs] 
2022-03-13T20:48:56.861+0800: [GC (Allocation Failure) [PSYoungGen: 58878K->19007K(116736K)] 340850K->300979K(466432K), 0.0027495 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:48:56.880+0800: [GC (Allocation Failure) [PSYoungGen: 77815K->20848K(116736K)] 359787K->320529K(466432K), 0.0039061 secs] [Times: user=0.20 sys=0.00, real=0.00 secs] 
2022-03-13T20:48:56.900+0800: [GC (Allocation Failure) [PSYoungGen: 79652K->20499K(116736K)] 379333K->340554K(466432K), 0.0061175 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2022-03-13T20:48:56.906+0800: [Full GC (Ergonomics) [PSYoungGen: 20499K->0K(116736K)] [ParOldGen: 320054K->299152K(349696K)] 340554K->299152K(466432K), [Metaspace: 3846K->3846K(1056768K)], 0.0313424 secs] [Times: user=0.38 sys=0.02, real=0.03 secs] 
2022-03-13T20:48:56.952+0800: [GC (Allocation Failure) [PSYoungGen: 58723K->25503K(116736K)] 357875K->324656K(466432K), 0.0031081 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:48:56.970+0800: [GC (Allocation Failure) [PSYoungGen: 84383K->19492K(116736K)] 383536K->343721K(466432K), 0.0064490 secs] [Times: user=0.14 sys=0.01, real=0.01 secs] 
2022-03-13T20:48:56.977+0800: [Full GC (Ergonomics) [PSYoungGen: 19492K->0K(116736K)] [ParOldGen: 324228K->308144K(349696K)] 343721K->308144K(466432K), [Metaspace: 3846K->3846K(1056768K)], 0.0325439 secs] [Times: user=0.31 sys=0.00, real=0.03 secs] 
执行结束!共生成对象次数:7328
Heap
 PSYoungGen      total 116736K, used 15980K [0x00000000f5580000, 0x0000000100000000, 0x0000000100000000)
  eden space 58880K, 27% used [0x00000000f5580000,0x00000000f651b100,0x00000000f8f00000)
  from space 57856K, 0% used [0x00000000f8f00000,0x00000000f8f00000,0x00000000fc780000)
  to   space 57856K, 0% used [0x00000000fc780000,0x00000000fc780000,0x0000000100000000)
 ParOldGen       total 349696K, used 308144K [0x00000000e0000000, 0x00000000f5580000, 0x00000000f5580000)
  object space 349696K, 88% used [0x00000000e0000000,0x00000000f2cec388,0x00000000f5580000)
 Metaspace       used 3852K, capacity 4540K, committed 4864K, reserved 1056768K
  class space    used 419K, capacity 428K, committed 512K, reserved 1048576K

Process finished with exit code 0
```


### CMSGC演示
XX:+UseConcMarkSweepGC -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps
```
正在执行...
2022-03-13T20:51:34.067+0800: [GC (Allocation Failure) 2022-03-13T20:51:34.067+0800: [ParNew: 139776K->17472K(157248K), 0.0228560 secs] 139776K->45859K(506816K), 0.0229675 secs] [Times: user=0.13 sys=0.13, real=0.02 secs] 
2022-03-13T20:51:34.137+0800: [GC (Allocation Failure) 2022-03-13T20:51:34.137+0800: [ParNew: 157248K->17459K(157248K), 0.0199736 secs] 185635K->89514K(506816K), 0.0200511 secs] [Times: user=0.03 sys=0.13, real=0.02 secs] 
2022-03-13T20:51:34.192+0800: [GC (Allocation Failure) 2022-03-13T20:51:34.192+0800: [ParNew: 157235K->17472K(157248K), 0.0419507 secs] 229290K->130834K(506816K), 0.0420200 secs] [Times: user=0.41 sys=0.03, real=0.04 secs] 
2022-03-13T20:51:34.268+0800: [GC (Allocation Failure) 2022-03-13T20:51:34.268+0800: [ParNew: 157248K->17471K(157248K), 0.0448344 secs] 270610K->176161K(506816K), 0.0449042 secs] [Times: user=0.45 sys=0.05, real=0.05 secs] 
2022-03-13T20:51:34.344+0800: [GC (Allocation Failure) 2022-03-13T20:51:34.344+0800: [ParNew: 157247K->17469K(157248K), 0.0449677 secs] 315937K->222318K(506816K), 0.0450414 secs] [Times: user=0.42 sys=0.06, real=0.04 secs] 
2022-03-13T20:51:34.390+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 204849K(349568K)] 222956K(506816K), 0.0005943 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:51:34.390+0800: [CMS-concurrent-mark-start]
2022-03-13T20:51:34.393+0800: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:51:34.393+0800: [CMS-concurrent-preclean-start]
2022-03-13T20:51:34.394+0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:51:34.394+0800: [CMS-concurrent-abortable-preclean-start]
2022-03-13T20:51:34.423+0800: [GC (Allocation Failure) 2022-03-13T20:51:34.423+0800: [ParNew: 157245K->17470K(157248K), 0.0468053 secs] 362094K->269768K(506816K), 0.0468735 secs] [Times: user=0.55 sys=0.06, real=0.05 secs] 
2022-03-13T20:51:34.502+0800: [GC (Allocation Failure) 2022-03-13T20:51:34.502+0800: [ParNew: 157246K->17471K(157248K), 0.0491241 secs] 409544K->316179K(506816K), 0.0491934 secs] [Times: user=0.48 sys=0.06, real=0.05 secs] 
2022-03-13T20:51:34.585+0800: [GC (Allocation Failure) 2022-03-13T20:51:34.585+0800: [ParNew: 157247K->17471K(157248K), 0.0525002 secs] 455955K->362614K(506816K), 0.0525949 secs] [Times: user=0.61 sys=0.05, real=0.05 secs] 
2022-03-13T20:51:34.638+0800: [CMS-concurrent-abortable-preclean: 0.005/0.244 secs] [Times: user=1.75 sys=0.19, real=0.24 secs] 
2022-03-13T20:51:34.638+0800: [GC (CMS Final Remark) [YG occupancy: 20400 K (157248 K)]2022-03-13T20:51:34.638+0800: [Rescan (parallel) , 0.0005862 secs]2022-03-13T20:51:34.639+0800: [weak refs processing, 0.0001106 secs]2022-03-13T20:51:34.639+0800: [class unloading, 0.0005511 secs]2022-03-13T20:51:34.640+0800: [scrub symbol table, 0.0008395 secs]2022-03-13T20:51:34.640+0800: [scrub string table, 0.0002616 secs][1 CMS-remark: 345143K(349568K)] 365543K(506816K), 0.0025811 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:51:34.641+0800: [CMS-concurrent-sweep-start]
2022-03-13T20:51:34.642+0800: [CMS-concurrent-sweep: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:51:34.643+0800: [CMS-concurrent-reset-start]
2022-03-13T20:51:34.644+0800: [CMS-concurrent-reset: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:51:34.676+0800: [GC (Allocation Failure) 2022-03-13T20:51:34.676+0800: [ParNew: 157140K->17471K(157248K), 0.0133938 secs] 457107K->360578K(506816K), 0.0134652 secs] [Times: user=0.19 sys=0.00, real=0.01 secs] 
2022-03-13T20:51:34.690+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 343106K(349568K)] 364224K(506816K), 0.0004640 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:51:34.690+0800: [CMS-concurrent-mark-start]
2022-03-13T20:51:34.692+0800: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:51:34.692+0800: [CMS-concurrent-preclean-start]
2022-03-13T20:51:34.693+0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:51:34.693+0800: [CMS-concurrent-abortable-preclean-start]
2022-03-13T20:51:34.693+0800: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:51:34.694+0800: [GC (CMS Final Remark) [YG occupancy: 33704 K (157248 K)]2022-03-13T20:51:34.694+0800: [Rescan (parallel) , 0.0005575 secs]2022-03-13T20:51:34.694+0800: [weak refs processing, 0.0000407 secs]2022-03-13T20:51:34.694+0800: [class unloading, 0.0005477 secs]2022-03-13T20:51:34.695+0800: [scrub symbol table, 0.0007578 secs]2022-03-13T20:51:34.696+0800: [scrub string table, 0.0002501 secs][1 CMS-remark: 343106K(349568K)] 376811K(506816K), 0.0022818 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
2022-03-13T20:51:34.696+0800: [CMS-concurrent-sweep-start]
2022-03-13T20:51:34.698+0800: [CMS-concurrent-sweep: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:51:34.698+0800: [CMS-concurrent-reset-start]
2022-03-13T20:51:34.699+0800: [CMS-concurrent-reset: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:51:34.727+0800: [GC (Allocation Failure) 2022-03-13T20:51:34.727+0800: [ParNew: 157247K->17470K(157248K), 0.0144586 secs] 389384K->296655K(506816K), 0.0145311 secs] [Times: user=0.00 sys=0.00, real=0.02 secs] 
2022-03-13T20:51:34.742+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 279184K(349568K)] 296847K(506816K), 0.0006429 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:51:34.742+0800: [CMS-concurrent-mark-start]
2022-03-13T20:51:34.744+0800: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.03 sys=0.00, real=0.00 secs] 
2022-03-13T20:51:34.744+0800: [CMS-concurrent-preclean-start]
2022-03-13T20:51:34.745+0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T20:51:34.746+0800: [CMS-concurrent-abortable-preclean-start]
2022-03-13T20:51:34.776+0800: [GC (Allocation Failure) 2022-03-13T20:51:34.776+0800: [ParNew: 157246K->17467K(157248K), 0.0140395 secs] 436431K->341275K(506816K), 0.0141161 secs] [Times: user=0.19 sys=0.00, real=0.01 secs] 
2022-03-13T20:51:34.826+0800: [GC (Allocation Failure) 2022-03-13T20:51:34.826+0800: [ParNew: 157243K->157243K(157248K), 0.0000984 secs]2022-03-13T20:51:34.826+0800: [CMS2022-03-13T20:51:34.826+0800: [CMS-concurrent-abortable-preclean: 0.003/0.080 secs] [Times: user=0.25 sys=0.00, real=0.08 secs] 
 (concurrent mode failure): 323807K->304804K(349568K), 0.0632201 secs] 481051K->304804K(506816K), [Metaspace: 3846K->3846K(1056768K)], 0.0634694 secs] [Times: user=0.06 sys=0.00, real=0.06 secs] 
执行结束!共生成对象次数:6537
Heap
 par new generation   total 157248K, used 70410K [0x00000000e0000000, 0x00000000eaaa0000, 0x00000000eaaa0000)
  eden space 139776K,  50% used [0x00000000e0000000, 0x00000000e44c2810, 0x00000000e8880000)
  from space 17472K,   0% used [0x00000000e9990000, 0x00000000e9990000, 0x00000000eaaa0000)
  to   space 17472K,   0% used [0x00000000e8880000, 0x00000000e8880000, 0x00000000e9990000)
 concurrent mark-sweep generation total 349568K, used 304804K [0x00000000eaaa0000, 0x0000000100000000, 0x0000000100000000)
 Metaspace       used 3852K, capacity 4540K, committed 4864K, reserved 1056768K
  class space    used 419K, capacity 428K, committed 512K, reserved 1048576K

Process finished with exit code 0
```

### G1GC演示
XX:+UseG1GC -Xms4g -Xmx4g -XX:+PrintGC -XX:+PrintGCDateStamps
```
正在执行...
2022-03-13T21:01:17.503+0800: [GC pause (G1 Evacuation Pause) (young) 204M->71M(4096M), 0.0214733 secs]
2022-03-13T21:01:17.569+0800: [GC pause (G1 Evacuation Pause) (young) 249M->129M(4096M), 0.0191418 secs]
2022-03-13T21:01:17.633+0800: [GC pause (G1 Evacuation Pause) (young) 307M->189M(4096M), 0.0181009 secs]
2022-03-13T21:01:17.689+0800: [GC pause (G1 Evacuation Pause) (young) 367M->234M(4096M), 0.0161870 secs]
2022-03-13T21:01:17.749+0800: [GC pause (G1 Evacuation Pause) (young) 412M->293M(4096M), 0.0206203 secs]
2022-03-13T21:01:17.812+0800: [GC pause (G1 Evacuation Pause) (young) 471M->348M(4096M), 0.0177098 secs]
2022-03-13T21:01:17.872+0800: [GC pause (G1 Evacuation Pause) (young) 526M->401M(4096M), 0.0182878 secs]
2022-03-13T21:01:17.943+0800: [GC pause (G1 Evacuation Pause) (young) 605M->468M(4096M), 0.0270801 secs]
2022-03-13T21:01:18.041+0800: [GC pause (G1 Evacuation Pause) (young) 726M->547M(4096M), 0.0280580 secs]
2022-03-13T21:01:18.139+0800: [GC pause (G1 Evacuation Pause) (young) 811M->628M(4096M), 0.0274048 secs]
2022-03-13T21:01:18.243+0800: [GC pause (G1 Evacuation Pause) (young) 926M->716M(4096M), 0.0554132 secs]
执行结束!共生成对象次数:8398

Process finished with exit code 0
```

## wrk压测演练
gateway-server-0.0.1-SNAPSHOT.jar
```
[root@localhost bin]# wrk -c 40 -d30s http://192.168.5.106:8088
Running 30s test @ http://192.168.5.106:8088
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     5.32ms    6.06ms 116.15ms   89.50%
    Req/Sec     2.21k     1.32k    9.02k    74.16%
  108900 requests in 30.08s, 27.02MB read
  Non-2xx or 3xx responses: 108900
Requests/sec:   3620.03
Transfer/sec:      0.90MB

``` 
NettyHttpServer
```
[root@localhost bin]# wrk -c 40 -d30s http://192.168.5.106:8808
Running 30s test @ http://192.168.5.106:8808
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     7.35ms   21.03ms 383.21ms   98.30%
    Req/Sec     3.78k   743.35     8.03k    77.76%
  223711 requests in 30.09s, 22.83MB read
Requests/sec:   7435.91
Transfer/sec:    776.99KB

```  
### 疑问
windows上的sb不知道为啥**有时候**没有结果，走一两秒就结束了😅
```
PS C:\Users\rfire_sun> sb -u http:localhost:8088 -c 40 -N 30
Starting at 2022/3/13 22:01:15
[Press C to stop the test]

---------------Finished!----------------
Finished at 2022/3/13 22:01:24 (took 00:00:09.1812177)
```

   
# 作业02 客户端HTTP调用
> 写一段代码，使用 HttpClient 或 OkHttp 访问  http://localhost:8801 ，代码提交到 GitHub。`

```java
package com.sun.geekbang.TrainingCamp.week02;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;



/**
 * ref:https://www.cnblogs.com/fnlingnzb-learner/p/10832471.html
 * 基本上从这里面copy来的
 */
public class HkHttpClient {

    private static final String BASE_URL = "http://localhost:8801";

    @Test
    public void apacheHttpClient() throws Exception {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(BASE_URL);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                //请求体内容
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");

                System.out.println(content);

                //内容写入文件
                FileUtils.writeStringToFile(new File("D:\\devtest\\resp.html"), content, "UTF-8");
                System.out.println("内容长度：" + content.length());
            }
        } finally {
            if (response != null) {
                response.close();
            }
            //相当于关闭浏览器
            httpclient.close();
        }
    }


    @Test
    public void okHttpClient() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(BASE_URL)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(Objects.requireNonNull(response.body()).string());
    }

}

```
