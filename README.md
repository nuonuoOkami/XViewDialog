# ViewDialog
![Image text](https://github.com/nuonuoOkami/images/blob/main/xdialog.png)
## 简单介绍
处理弹窗层级问题，不在纠结弹窗弹出问题。

纯原生view，随意使用!

只看level,等级越高层级越高！

因为实现了DialogInterface ，使用方法和普通Dialog一致。


## 依赖库

   无，纯原生


## 依赖使用

     implementation 'io.github.nuonuoOkami:ViewDialog:1.0.0'

## 使用方式

* 无业务处理
```
    //直接传入布局
        ViewDialog(MainActivity@ this, layout = R.layout.activity_main).show()
        // 支持传入View
        ViewDialog(
            MainActivity@ this,
            rootView = LayoutDialogBinding.inflate(layoutInflater).root
        ).show()
```
* 内部业务处理
 ```
     //自定义Demo
     class DemoDialog2(content: Activity) :
    ViewDialog(content, rootView = LayoutDialogBinding.inflate(content.layoutInflater).root) {


    //控件处理
    override fun bindUI(rootView: View) {
        super.bindUI(rootView)
    }

    //level 越大 显示的时候就在上面
    override fun level() = 3

    //默认warp
    override fun params(): LayoutParams {
        val layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        layoutParams.gravity = gravity()
        return layoutParams
    }

    //同类型只能存在一个 避免多次弹窗
    override fun single()=true
    //是否弹窗变黑
    override fun isDark()=true
    //支持响应事件
    override  fun   canceledAble()=true

    //是否可以点击外部取消
    override  fun canceledOnTouchOutside() = true
    //业务处理
    override fun business() {
        super.business()
    }}

      //直接调用show()和dismiss()就行，无需其他操作
        val dialog2= DemoDialog2(MainActivity@this)
        dialog2 .show()
        dialog2.dismiss()
```


## Change Log
暂无

## 联系我
QQ:1085214089

## 博客
https://www.jianshu.com/p/0bc335fb71ca
    
    
    

