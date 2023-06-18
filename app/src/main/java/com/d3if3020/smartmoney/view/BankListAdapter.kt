import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.d3if3020.smartmoney.R
import com.d3if3020.smartmoney.databinding.ListItemBankBinding
import com.d3if3020.smartmoney.model.BankList
import com.d3if3020.smartmoney.network.BankListApi

class BankListAdapter : ListAdapter<BankList, BankListAdapter.ViewHolder>(BankListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBankBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ListItemBankBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bankList: BankList) {
            with(binding) {
                tvBankName.text = bankList.bankName
                tvBankTelp.text = bankList.bankTelp
                tvBankWebsite.text = bankList.bankWebsite
                Glide.with(binding.bankLogo.context)
                    .load(BankListApi.getBankListUrl(bankList.bankLogo))
                    .error(R.drawable.baseline_broken_image_24)
                    .into(binding.bankLogo)
            }
        }
    }
}

class BankListDiffCallback : DiffUtil.ItemCallback<BankList>() {
    override fun areItemsTheSame(oldItem: BankList, newItem: BankList): Boolean {
        return oldItem.bankName == newItem.bankName
    }

    override fun areContentsTheSame(oldItem: BankList, newItem: BankList): Boolean {
        return oldItem == newItem
    }
}
