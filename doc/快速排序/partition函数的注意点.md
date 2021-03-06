#### 1.对于与pivot值相同的元素的处理
```java
//找到一个大于等于arr[l]的数arr[i]
 while (i <= j && arr[i] < arr[l]) {
    i++;
 }
//找到一个小于等于arr[l]的数arr[j]
while (i <= j && arr[j] > arr[l]) {
	j--;
}
```
以上的代码中，能否将

```java
arr[i] < arr[l]
arr[j] > arr[l]
```

改为

``` java
arr[i] <= arr[l]
arr[j] >= arr[l]
```

答案是最好不要，因为对于一个常数数组来说，这样的改动会导致算法时间复杂度退化为![](https://latex.codecogs.com/svg.latex?O(N^2))。

#### 2.对于已经有序的数组的处理

比如arr是一个500W大小的数组，里面存的是1-500W。如果还是取arr[l]作为pivot，那每次取的都是数组里最小的。同样也会导致算法时间复杂度退化为![](https://latex.codecogs.com/svg.latex?O(N^2))。

解决办法就是增加随机化：先随机取一个数，再把这个数和arr[l]交换下，再开始分区。也就是如下所示代码：

```java
//问题：对于已经有序的数组，总是选取arr[l]作为pivot会导致快速排序退化为O(N^2)的算法，数据量大的的时候甚至会栈溢出。
//解决办法：随机选取一个arr[l,r]的一个数作为pivot
int p = random.nextInt(r - l + 1) + l;
//交换一下，再开始分区
swap(arr, l, p);
```

可能有人会问：为什么不可以直接取中位数，然后和arr[l]交换？

因为不论是取第一个数也好，还是取中位数，或者其他位置的数，都可以构造出特定的测试用例来使得算法退化，而随机取值就能最大程度地避免这个问题。实际上，加上随机化后的快排，其退化的概率是![](https://latex.codecogs.com/svg.latex?\frac{1}{n!})。这是因为：第一次分区，随机取的那个数是最小值的概率是![](https://latex.codecogs.com/svg.latex?\frac{1}{n})，第二次分区仍然取到最小值的概率是![](https://latex.codecogs.com/svg.latex?\frac{1}{n-1})...，以此类推，可以计算出其总概率是![](https://latex.codecogs.com/svg.latex?\frac{1}{n!})。

