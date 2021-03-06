package eu.balzo.authdroid.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.balzo.authdroid.auth.R
import eu.balzo.authdroid.core.SocialAuthUser
import kotlinx.android.synthetic.main.profile.*

class ProfileActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(R.layout.profile)

        this.name_value.text = this.intent.getStringExtra(NAME) ?: "/"
        this.first_name_value.text = this.intent.getStringExtra(FIRST_NAME) ?: "/"
        this.last_name_value.text = this.intent.getStringExtra(LAST_NAME) ?: "/"
        this.email_value.text = this.intent.getStringExtra(EMAIL) ?: "/"
        this.id_value.text = this.intent.getStringExtra(ID) ?: "/"
        this.token_value.text = this.intent.getStringExtra(TOKEN) ?: "/"

        Glide.with(this)
                .load(this.intent.getStringExtra(PROFILE_IMAGE).orEmpty())
                .error(R.drawable.profile)
                .into(this.profile_image)
    }

    companion object {

        private const val EMAIL = "email"
        private const val NAME = "name"
        private const val FIRST_NAME = "first_name"
        private const val LAST_NAME = "last_name"
        private const val ID = "id"
        private const val TOKEN = "token"
        private const val PROFILE_IMAGE = "profile_image"

        fun start(
                context: Context,
                user: SocialAuthUser
        ) {
            val intent = Intent(context, ProfileActivity::class.java)

            intent.putExtra(NAME, user.displayName)
            intent.putExtra(FIRST_NAME, user.firstName)
            intent.putExtra(LAST_NAME, user.lastName)
            intent.putExtra(EMAIL, user.email)
            intent.putExtra(ID, user.id)
            intent.putExtra(TOKEN, user.token)
            intent.putExtra(PROFILE_IMAGE, user.profileImage)

            context.startActivity(intent)
        }
    }
}