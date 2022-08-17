package cn.lhsearch.rcs.entity;

import javax.persistence.*;

@Entity
@Table(name = "consultant_user")
public class User {
    @Id
	@Column(name = "wechat_open_id")
    private String weChatOpenId;

	@Column(name = "wechat_union_id")
    private String weChatUnionId;

	@Column(name = "wechat_nick_name")
    private String weChatNickName;

    @Column(name = "is_approved")
    private boolean isApproved;

    public String getWeChatOpenId()
    {
        return this.weChatOpenId;
    }

    public void setWeChatOpenId(String value)
    {
        this.weChatOpenId = value;
    }

    public String getWeChatUnionId()
    {
        return this.weChatUnionId;
    }

    public void setWeChatUnionId(String value)
    {
        this.weChatUnionId = value;
    }

    public String getWeChatNickName()
    {
        return this.weChatNickName;
    }

    public void setWeChatNickName(String value)
    {
        this.weChatNickName = value;
    }

    public boolean getIsApproved()
    {
        return this.isApproved;
    }

    public void setIsApproved(boolean value)
    {
        this.isApproved = value;
    }
}
