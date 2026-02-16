package dev.akexorcist.adaptivescrolling.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.core.content.withStyledAttributes

class AdaptiveVerticalScrollLayout : FrameLayout {
    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    @Suppress("unused")
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs)
    }

    private lateinit var scrollView: ScrollView
    private lateinit var contentLayout: LinearLayout

    private var adaptiveBehaviorEnabled: Boolean = true
    private var nonScrollableGravity: Int = Gravity.CENTER
    private var scrollableGravity: Int = Gravity.TOP or Gravity.CENTER_HORIZONTAL
    private var showScrollbar: Boolean = true
    private var isScrollableState: Boolean = false
    private var onScrollableStateChangeListener: OnScrollableStateChangeListener? = null

    private fun init(attrs: AttributeSet?) {
        val view = LayoutInflater.from(context).inflate(R.layout.avsl_layout_adaptive_scrolling, this, true)
        scrollView = view.findViewById(R.id.avsl_scroll_view)
        contentLayout = view.findViewById(R.id.avsl_content_layout)

        attrs?.let {
            context.withStyledAttributes(it, R.styleable.AdaptiveVerticalScrollLayout) {
                adaptiveBehaviorEnabled = getBoolean(
                    R.styleable.AdaptiveVerticalScrollLayout_avsl_adaptiveBehaviorEnabled,
                    true
                )
                nonScrollableGravity = getInt(
                    R.styleable.AdaptiveVerticalScrollLayout_avsl_nonScrollableGravity,
                    Gravity.CENTER
                )
                scrollableGravity = getInt(
                    R.styleable.AdaptiveVerticalScrollLayout_avsl_scrollableGravity,
                    Gravity.TOP or Gravity.CENTER_HORIZONTAL
                )
                showScrollbar = getBoolean(
                    R.styleable.AdaptiveVerticalScrollLayout_avsl_showScrollbar,
                    true
                )
            }
        }

        scrollView.isVerticalScrollBarEnabled = showScrollbar
        setupAdaptiveBehavior()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (isInEditMode) {
            /*
             In the Layout Editor, call`updateAdaptiveState()`
             directly during the layout pass to render the
             final state and fix the preview.

             At runtime, this is handled by `scrollView.post`.
             */
            updateAdaptiveState()
        }
    }
    /**
     * Called after the view and all its children have been inflated from XML.
     * This is the ideal place to take the children defined in the XML layout
     * and move them into our internal `contentLayout`.
     */
    override fun onFinishInflate() {
        super.onFinishInflate()
        if (childCount > 1) {
            val childrenToMove = (1 until childCount).map { getChildAt(it) }.toList()
            removeViews(1, childrenToMove.size)
            childrenToMove.forEach { child ->
                contentLayout.addView(child)
            }
        }
    }

    private fun setupAdaptiveBehavior() {
        contentLayout.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            scrollView.post {
                updateAdaptiveState()
            }
        }
    }

    private fun updateAdaptiveState() {
        if (!adaptiveBehaviorEnabled) return

        val currentlyScrollable = contentLayout.height > scrollView.height
        if (isScrollableState != currentlyScrollable) {
            isScrollableState = currentlyScrollable
            onScrollableStateChangeListener?.onScrollableStateChanged(isScrollableState)
        }

        scrollView.overScrollMode = if (isScrollableState) OVER_SCROLL_ALWAYS else OVER_SCROLL_NEVER
        contentLayout.gravity = if (isScrollableState) scrollableGravity else nonScrollableGravity
    }

    /**
     * Returns the internal LinearLayout that serves as the content container.
     * Use this to add or remove views programmatically.
     */
    @Suppress("unused")
    fun getContentLayout(): LinearLayout = contentLayout

    /**
     * Returns whether the content is currently scrollable.
     */
    @Suppress("unused")
    fun isScrollable(): Boolean = isScrollableState

    /**
     * Sets a listener to be notified when the scrollable state changes.
     */
    @Suppress("unused")
    fun setOnScrollableStateChangeListener(listener: OnScrollableStateChangeListener?) {
        this.onScrollableStateChangeListener = listener
        listener?.onScrollableStateChanged(isScrollableState)
    }

    fun interface OnScrollableStateChangeListener {
        fun onScrollableStateChanged(isScrollable: Boolean)
    }
}
