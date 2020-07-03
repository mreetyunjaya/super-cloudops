/*
 * Copyright 2017 ~ 2025 the original author or authors. <wanglsir@gmail.com, 983708408@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wl4g.devops.erm.service.impl;

import com.github.pagehelper.PageHelper;
import com.wl4g.devops.common.bean.BaseBean;
import com.wl4g.devops.common.bean.erm.DnsPublicDomain;
import com.wl4g.devops.dao.erm.DnsPublicDomainDao;
import com.wl4g.devops.erm.service.DnsPublicDomainService;
import com.wl4g.devops.page.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.wl4g.devops.iam.common.utils.IamOrganizationHolder.getRequestOrganizationCode;
import static com.wl4g.devops.iam.common.utils.IamOrganizationHolder.getRequestOrganizationCodes;
import static java.util.Objects.isNull;

/**
 * @author vjay
 * @date 2019-11-14 14:10:00
 */
@Service
public class DnsPublicDomainServiceImpl implements DnsPublicDomainService {

    @Autowired
    private DnsPublicDomainDao dnsPublicDomainDao;

    @Override
    public PageModel page(PageModel pm,String name) {
        pm.page(PageHelper.startPage(pm.getPageNum(), pm.getPageSize(), true));
        pm.setRecords(dnsPublicDomainDao.list(getRequestOrganizationCodes(), name));
        return pm;
    }

    public void save(DnsPublicDomain dnsPublicDomain){
        if(isNull(dnsPublicDomain.getId())){
            dnsPublicDomain.preInsert(getRequestOrganizationCode());
            insert(dnsPublicDomain);
        }else{
            dnsPublicDomain.preUpdate();
            update(dnsPublicDomain);
        }
    }

    private void insert(DnsPublicDomain dnsPublicDomain){
        dnsPublicDomainDao.insertSelective(dnsPublicDomain);
    }

    private void update(DnsPublicDomain dnsPublicDomain){
        dnsPublicDomainDao.updateByPrimaryKeySelective(dnsPublicDomain);
    }


    public DnsPublicDomain detail(Integer id){
        Assert.notNull(id,"id is null");
        return dnsPublicDomainDao.selectByPrimaryKey(id);
    }

    public void del(Integer id){
        Assert.notNull(id,"id is null");
        DnsPublicDomain dnsPublicDomain = new DnsPublicDomain();
        dnsPublicDomain.setId(id);
        dnsPublicDomain.setDelFlag(BaseBean.DEL_FLAG_DELETE);
        dnsPublicDomainDao.updateByPrimaryKeySelective(dnsPublicDomain);
    }



}